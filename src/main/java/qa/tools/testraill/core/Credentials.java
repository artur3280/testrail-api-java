package qa.tools.testraill.core;

import lombok.*;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Credentials {
    @NonNull
    private String baseUrl;
    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private String appName;
    private Map<Object, Object> additionalFields = new HashMap<>();

    public String token() {
        return "Basic " + DatatypeConverter.printBase64Binary((getUsername() + ":" + getPassword()).getBytes(StandardCharsets.UTF_8));
    }

    @SneakyThrows
    public Credentials(String propertyPath) {
        Properties properties = new Properties();
        if (new File(propertyPath).exists()) {
            try (FileInputStream file = new FileInputStream(propertyPath)) {
                properties.load(file);
            }

            setBaseUrl(properties.getProperty("tr.host"));
            setUsername(properties.getProperty("tr.user_name"));
            setPassword(properties.getProperty("tr.password"));
            setAppName(properties.getProperty("tr.appName"));
        }
    }
}
