package qa.tools.testraill.models.sections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import qa.tools.testraill.requests.Sections;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "depth",
        "display_order",
        "id",
        "name",
        "parent_id",
        "suite_id"
})
public class Section {
    @JsonProperty("depth")
    private Integer depth;
    @JsonProperty("display_order")
    private Integer displayOrder;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    @JsonView({Sections.Add.class, Sections.Update.class})
    private String name;
    @JsonProperty("parent_id")
    @JsonView({Sections.Add.class, Sections.Update.class})
    private Integer parentId;
    @JsonProperty("suite_id")
    @JsonView({Sections.Add.class, Sections.Update.class})
    private Integer suiteId;
    @JsonProperty("description")
    @JsonView({Sections.Add.class, Sections.Update.class})
    private String description;
}
