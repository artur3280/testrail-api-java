package qa.tools.testraill.models.suites;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import qa.tools.testraill.requests.Suites;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "description",
        "id",
        "name",
        "project_id",
        "url"
})
public class Suite {
    @JsonProperty("description")
    @JsonView({Suites.Add.class, Suites.Update.class})
    private String description;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    @JsonView({Suites.Add.class, Suites.Update.class})
    private String name;
    @JsonProperty("project_id")
    private Integer project_id;
    @JsonProperty("url")
    private String url;
}
