package qa.tools.testraill.models.sections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

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
    private String name;
    @JsonProperty("parent_id")
    private Integer parentId;
    @JsonProperty("suite_id")
    private Integer suiteId;
}
