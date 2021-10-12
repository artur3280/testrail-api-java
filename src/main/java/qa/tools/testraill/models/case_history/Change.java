package qa.tools.testraill.models.case_history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.annotation.Generated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type_id",
        "old_text",
        "new_text",
        "field",
        "old_value",
        "new_value"
})
@Generated("jsonschema2pojo")
public class Change {

    @JsonProperty("type_id")
    private Integer typeId;
    @JsonProperty("old_text")
    private String oldText;
    @JsonProperty("new_text")
    private String newText;
    @JsonProperty("field")
    private String field;
    @JsonProperty("old_value")
    private Integer oldValue;
    @JsonProperty("new_value")
    private Integer newValue;
}
