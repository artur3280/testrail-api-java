package qa.tools.testraill.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import qa.tools.testraill.TestRail;
import qa.tools.testraill.core.internal.UrlConnectionFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public abstract class Requester<T> {
    @javax.annotation.Generated("lombok")
    private final String method;
    private final String restPath;
    private final Class<? extends T> responseClass;
    private final TypeToken<? extends T> responseType;
    private static final UrlConnectionFactory DEFAULT_URL_CONNECTION_FACTORY = new UrlConnectionFactory();
    private UrlConnectionFactory urlConnectionFactory = DEFAULT_URL_CONNECTION_FACTORY;
    private static final ObjectMapper JSON = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES)
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    private Requester(@NonNull final String method, @NonNull final String restPath, final Class<? extends T> responseClass, final TypeToken<? extends T> responseType) {
        if (method.isEmpty()) {
            throw new NullPointerException("method");
        }
        if (restPath == null) {
            throw new NullPointerException("restPath");
        }
        this.method = method;
        this.restPath = restPath;
        this.responseClass = responseClass;
        this.responseType = responseType;
    }

    public Requester(@NonNull String method, String restPath, @NonNull Class<? extends T> responseClass) {
        this(method, restPath, responseClass, null);
        if (responseClass == null) {
            throw new NullPointerException("responseClass");
        }
    }

    public Requester(@NonNull String method, String restPath, @NonNull TypeToken<? extends T> responseType) {
        this(method, restPath, null, responseType);
        if (responseType == null) {
            throw new NullPointerException("responseType");
        }
    }

    public T execute() {
        try {
            String url = getUrl();
            HttpURLConnection con = (HttpURLConnection) urlConnectionFactory.getUrlConnection(url);
            con.setRequestMethod(method);

            if (TestRail.appName() != null) {
                con.setRequestProperty("User-Agent", TestRail.appName());
            }

            con.setRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("x-api-ident", "beta");
            con.setRequestProperty("Authorization", TestRail.token());

            if (method.equals("POST")) {
                con.setDoOutput(true);
                Object content = getContent();
                if (content != null) {
                    try (OutputStream outputStream = new BufferedOutputStream(con.getOutputStream())) {
//                        outputStream.write(new Gson().toJson(content).getBytes(StandardCharsets.UTF_8));
                        JSON.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                        outputStream.write(JSON.writeValueAsString(content).getBytes(StandardCharsets.UTF_8));
                    }
                } else {
                    con.setFixedLengthStreamingMode(0);
                }
            }

            System.out.println("Sending " + method + " request to URL : " + url);
            int responseCode = 0;
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                // swallow it since for 401 getResponseCode throws an IOException
                responseCode = con.getResponseCode();
            }

            System.out.println("Response Code : " + responseCode);

            if (responseCode != HttpURLConnection.HTTP_OK) {
                try (InputStream errorStream = con.getErrorStream()) {
                    APIException.Builder exceptionBuilder = new APIException.Builder().setResponseCode(responseCode);
                    if (errorStream == null) {
                        throw exceptionBuilder.setError("<server did not send any error message>").build();
                    }
                    throw JSON.readerForUpdating(exceptionBuilder).<APIException.Builder>readValue(new BufferedInputStream(errorStream)).build();
                }
            }

            try (InputStream responseStream = new BufferedInputStream(con.getInputStream())) {
                String responseValue = new BufferedReader(
                        new InputStreamReader(responseStream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

                System.out.println(responseValue);
                Object supplementForDeserialization = getSupplementForDeserialization();
                if (responseClass != null) {
                    if (responseClass == Void.class) {
                        return null;
                    }

//                    if (supplementForDeserialization != null) {
//                        return JSON.readerFor(responseClass)
//                                .with(new InjectableValues
//                                        .Std()
//
//                                        .addValue(responseClass, supplementForDeserialization))
//                                .readValue(responseStream);
//                    }
                    return new Gson().fromJson(responseValue, responseClass);
                } else {
                    return new Gson().fromJson(responseValue, responseType.getType());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getUrl() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(TestRail.url()).append(restPath);
        String queryParamJson = JSON.writerWithView(getClass()).writeValueAsString(this);
        String queryParamString = JSON.readValue(queryParamJson, QueryParameterString.class).toString();
        if (!queryParamString.isEmpty()) {
            urlBuilder.append("&").append(queryParamString);
        }
        return urlBuilder.toString();
    }


    /**
     * Override this method to provide content to be send with {@code Method#POST} requests.
     *
     * @return content
     */
    public Object getContent() {
        return null;
    }

    /**
     * Override this method to provide supplementary information to deserializer.
     *
     * @return any object acting as supplement for deserialization
     */
    public Object getSupplementForDeserialization() {
        return null;
    }

    /**
     * Set URL connection factory. Only used for testing.
     *
     * @param urlConnectionFactory the URL connection factory
     */
    void setUrlConnectionFactory(UrlConnectionFactory urlConnectionFactory) {
        this.urlConnectionFactory = urlConnectionFactory;
    }

//    public static Object execute_old(String method, String uri, Object data)
//            throws MalformedURLException, IOException, APIException {
//        URL url = new URL(TestRail.url());
//        // Create the connection object and set the required HTTP method
//        // (GET/POST) and headers (content type and basic auth).
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//        conn.addRequestProperty("Authorization", TestRail.token());
//
//        if (method.equals("POST")) {
//            conn.setRequestMethod("POST");
//            // Add the POST arguments, if any. We just serialize the passed
//            // data object (i.e. a dictionary) and then add it to the
//            // request body.
//            if (data != null) {
//                if (uri.startsWith("add_attachment")) {
//                    String boundary = "TestRailAPIAttachmentBoundary"; //Can be any random string
//                    File uploadFile = new File((String) data);
//
//                    conn.setDoOutput(true);
//                    conn.addRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
//
//                    OutputStream ostreamBody = conn.getOutputStream();
//                    BufferedWriter bodyWriter = new BufferedWriter(new OutputStreamWriter(ostreamBody));
//
//                    bodyWriter.write("\n\n--" + boundary + "\r\n");
//                    bodyWriter.write("Content-Disposition: form-data; name=\"attachment\"; filename=\""
//                            + uploadFile.getName() + "\"");
//                    bodyWriter.write("\r\n\r\n");
//                    bodyWriter.flush();
//
//                    //Read file into request
//                    InputStream istreamFile = new FileInputStream(uploadFile);
//                    int bytesRead;
//                    byte[] dataBuffer = new byte[1024];
//                    while ((bytesRead = istreamFile.read(dataBuffer)) != -1) {
//                        ostreamBody.write(dataBuffer, 0, bytesRead);
//                    }
//
//                    ostreamBody.flush();
//
//                    //end of attachment, add boundary
//                    bodyWriter.write("\r\n--" + boundary + "--\r\n");
//                    bodyWriter.flush();
//
//                    //Close streams
//                    istreamFile.close();
//                    ostreamBody.close();
//                    bodyWriter.close();
//                } else    // Not an attachment
//                {
//                    conn.addRequestProperty("Content-Type", "application/json");
//                    conn.addRequestProperty("x-api-ident", "beta");
//                    byte[] block = JSONValue.toJSONString(data).
//                            getBytes("UTF-8");
//
//                    conn.setDoOutput(true);
//                    OutputStream ostream = conn.getOutputStream();
//                    ostream.write(block);
//                    ostream.close();
//                }
//            }
//        } else    // GET request
//        {
//            conn.addRequestProperty("Content-Type", "application/json");
//            conn.addRequestProperty("x-api-ident", "beta");
//        }
//
//        // Execute the actual web request (if it wasn't already initiated
//        // by getOutputStream above) and record any occurred errors (we use
//        // the error stream in this case).
//        int status = conn.getResponseCode();
//
//        InputStream istream;
//        if (status != 200) {
//            istream = conn.getErrorStream();
//            if (istream == null) {
//                throw new APIException(
//                        "TestRail API return HTTP " + status +
//                                " (No additional error message received)"
//                );
//            }
//        } else {
//            istream = conn.getInputStream();
//        }
//
//        // If 'get_attachment' (not 'get_attachments') returned valid status code, save the file
//        if ((istream != null) && (uri.startsWith("get_attachment/"))) {
//            FileOutputStream outputStream = new FileOutputStream((String) data);
//
//            int bytesRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((bytesRead = istream.read(buffer)) > 0) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            outputStream.close();
//            istream.close();
//            return (String) data;
//        }
//
//        // Not an attachment received
//        // Read the response body, if any, and deserialize it from JSON.
//        String text = "";
//        if (istream != null) {
//            BufferedReader reader = new BufferedReader(
//                    new InputStreamReader(
//                            istream,
//                            "UTF-8"
//                    )
//            );
//
//            String line;
//            while ((line = reader.readLine()) != null) {
//                text += line;
//                text += System.getProperty("line.separator");
//            }
//
//            reader.close();
//        }
//
//        Object result;
//        if (!text.equals("")) {
//            Gson gson = new Gson();
//            gson.fromJson(text, )
//            result = JSONValue.parse(text);
//        } else {
//            result = new JSONObject();
//        }
//
//        // Check for any occurred errors and add additional details to
//        // the exception message, if any (e.g. the error message returned
//        // by TestRail).
//        if (status != 200) {
//            String error = "No additional error message received";
//            if (result != null && result instanceof JSONObject) {
//                JSONObject obj = (JSONObject) result;
//                if (obj.containsKey("error")) {
//                    error = '"' + (String) obj.get("error") + '"';
//                }
//            }
//
//            throw new APIException(
//                    "TestRail API returned HTTP " + status +
//                            "(" + error + ")"
//            );
//        }
//
//        return result;
//    }
}
