package qa.tools.testraill.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import lombok.NonNull;
import lombok.SneakyThrows;
import qa.tools.testraill.TestRail;
import qa.tools.testraill.core.internal.UrlConnectionFactory;

import java.io.*;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class Requester<T> {
    @javax.annotation.Generated("lombok")
    private final String method;
    private final String restPath;
    private final Class<? extends T> responseClass;
    private final TypeReference<? extends T> responseType;
    private static final UrlConnectionFactory DEFAULT_URL_CONNECTION_FACTORY = new UrlConnectionFactory();
    private UrlConnectionFactory urlConnectionFactory = DEFAULT_URL_CONNECTION_FACTORY;
    private static final ObjectMapper JSON = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    private String responseForMap = null;

    private Requester(@NonNull final String method, @NonNull final String restPath, final Class<? extends T> responseClass, final TypeReference<? extends T> responseType) {
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

    public Requester(@NonNull String method, String restPath, @NonNull TypeReference<? extends T> responseType) {
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

            CustomLogger.log.debug("User token:".concat(TestRail.token()));

            con.setRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("x-api-ident", "beta");
            con.setRequestProperty("Authorization", TestRail.token());

            if (method.equals("POST")) {
                con.setDoOutput(true);

                Object content = getContent();
                if (content != null) {
                    try (OutputStream outputStream = new BufferedOutputStream(con.getOutputStream())) {
                        JSON.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                        outputStream.write(JSON.writeValueAsString(content).getBytes(StandardCharsets.UTF_8));
                    }
                } else {
                    con.setFixedLengthStreamingMode(0);
                }
            }

            CustomLogger.log.debug("\nRequest: ".concat(method).concat(":").concat(url)
                    .concat("\n")
                    .concat(getContent() != null ? JSON.writerWithDefaultPrettyPrinter().writeValueAsString(getContent()) : "<body empty>"));

            int responseCode = 0;
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                responseCode = con.getResponseCode();
            }

            CustomLogger.log.debug("\nStatus code:" + JSON.writerWithDefaultPrettyPrinter().writeValueAsString(responseCode));

            if (responseCode != HttpURLConnection.HTTP_OK) {
                CustomLogger.log.debug("\nStatus code:" + JSON.writerWithDefaultPrettyPrinter().writeValueAsString(responseCode));

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

                CustomLogger.log.debug("\nResponse:" +
                        JSON.writerWithDefaultPrettyPrinter()
                                .writeValueAsString(JSON.readValue(responseValue, Object.class)));

                Object supplementForDeserialization = getSupplementForDeserialization();

                if (responseClass != null) {
                    if (responseClass == Void.class) {
                        return null;
                    }
                    return responseClassDeserializer(responseValue, supplementForDeserialization, (Class<? extends T>) responseClass);
                } else {
                    return responseTypeDeserializer(responseValue, supplementForDeserialization, (TypeReference<? extends T>) responseType);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Requester<T> execAs() {
        try {
            String url = getUrl();
            HttpURLConnection con = (HttpURLConnection) urlConnectionFactory.getUrlConnection(url);
            con.setRequestMethod(method);

            if (TestRail.appName() != null) {
                con.setRequestProperty("User-Agent", TestRail.appName());
            }

            CustomLogger.log.debug("User token:".concat(TestRail.token()));

            con.setRequestProperty("Content-Type", "application/json");
            con.addRequestProperty("x-api-ident", "beta");
            con.setRequestProperty("Authorization", TestRail.token());

            if (method.equals("POST")) {
                con.setDoOutput(true);

                Object content = getContent();
                if (content != null) {
                    try (OutputStream outputStream = new BufferedOutputStream(con.getOutputStream())) {
                        JSON.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                        outputStream.write(JSON.writeValueAsString(content).getBytes(StandardCharsets.UTF_8));
                    }
                } else {
                    con.setFixedLengthStreamingMode(0);
                }
            }

            CustomLogger.log.debug("\nRequest: ".concat(method).concat(":").concat(url)
                    .concat("\n")
                    .concat(getContent() != null ? JSON.writerWithDefaultPrettyPrinter().writeValueAsString(getContent()) : "<body empty>"));

            int responseCode = 0;
            try {
                responseCode = con.getResponseCode();
            } catch (IOException e) {
                responseCode = con.getResponseCode();
            }

            CustomLogger.log.debug("\nStatus code:" + JSON.writerWithDefaultPrettyPrinter().writeValueAsString(responseCode));

            if (responseCode != HttpURLConnection.HTTP_OK) {
                CustomLogger.log.debug("\nStatus code:" + JSON.writerWithDefaultPrettyPrinter().writeValueAsString(responseCode));

                try (InputStream errorStream = con.getErrorStream()) {
                    APIException.Builder exceptionBuilder = new APIException.Builder().setResponseCode(responseCode);
                    if (errorStream == null) {
                        throw exceptionBuilder.setError("<server did not send any error message>").build();
                    }
                    throw JSON.readerForUpdating(exceptionBuilder).<APIException.Builder>readValue(new BufferedInputStream(errorStream)).build();
                }
            }

            try (InputStream responseStream = new BufferedInputStream(con.getInputStream())) {
                responseForMap
                        = new BufferedReader(
                        new InputStreamReader(responseStream, StandardCharsets.UTF_8))
                        .lines()
                        .collect(Collectors.joining("\n"));

                CustomLogger.log.debug("\nResponse:" + JSON.writerWithDefaultPrettyPrinter().writeValueAsString(JSON.readValue(responseForMap, Object.class)));

                return this;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public <T> T as(Class<T> cls) {
        if (responseForMap == null)
            throw new NullPointerException("Response is null");
        return JSON.readValue(responseForMap, cls);
    }

    @SneakyThrows
    public <T> T as(TypeReference<T> typeRef) {
        if (responseForMap == null)
            throw new NullPointerException("Response is null");
        return JSON.readValue(responseForMap, typeRef);
    }

    private T responseTypeDeserializer(String responseValue, Object supplementForDeserialization, TypeReference<? extends T> responseType) throws com.fasterxml.jackson.core.JsonProcessingException {
        if (supplementForDeserialization != null) {
            String supplementKey = responseType.getType().toString();
            if (responseType.getType() instanceof ParameterizedType) {
                Type[] actualTypes = ((ParameterizedType) responseType.getType()).getActualTypeArguments();
                if (actualTypes.length == 1 && actualTypes[0] instanceof Class<?>) {
                    supplementKey = actualTypes[0].toString();
                }
            }

            return JSON.readerFor(responseType)
                    .with(new InjectableValues.Std()
                            .addValue(supplementKey, supplementForDeserialization))
                    .readValue(responseValue);
        }

        return JSON.readValue(responseValue, responseType);
    }

    private T responseClassDeserializer(String responseValue, Object supplementForDeserialization, Class<? extends T> responseClass) throws com.fasterxml.jackson.core.JsonProcessingException {
        if (supplementForDeserialization != null) {
            return JSON.readerFor(responseClass)
                    .with(
                            new InjectableValues.Std()
                                    .addValue(
                                            responseClass.toString(), supplementForDeserialization))
                    .readValue(responseValue);
        }
        return JSON.readValue(responseValue, responseClass);
    }


    private String getUrl() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(TestRail.url()).append(restPath);
        String queryParamString = getQueryParams();
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
     * Override this method to provide filters for {@code Method#GET} requests.
     *
     * @return String
     */
    private String queryParams = "";

    public Requester<T> queryParams(Map<Object, Object> queryParams) {
        this.queryParams = Joiner.on("&").withKeyValueSeparator("=").join(queryParams);
        return this;
    }

    public Requester<T> queryParam(Object key, Object value) {
        this.queryParams = Joiner
                .on("&")
                .withKeyValueSeparator("=")
                .join(ImmutableMap.<Object, Object>builder().put(key, value).build());
        return this;
    }

    private String getQueryParams() {
        return queryParams;
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
}
