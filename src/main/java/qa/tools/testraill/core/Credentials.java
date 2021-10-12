package qa.tools.testraill.core;

import lombok.*;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;

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

    public String token() {
        return "Basic " + DatatypeConverter.printBase64Binary((getUsername() + ":" + getPassword()).getBytes(StandardCharsets.UTF_8));
    }
}
