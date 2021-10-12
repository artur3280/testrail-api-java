package qa.tools.testraill.models.case_history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "type_id",
        "created_on",
        "user_id",
        "changes"
})
public class Item {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("type_id")
    private Integer typeId;
    @JsonProperty("created_on")
    private Integer createdOn;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("changes")
    private List<Change> changes = null;
}
