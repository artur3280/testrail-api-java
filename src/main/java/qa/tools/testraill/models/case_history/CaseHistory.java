package qa.tools.testraill.models.case_history;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import qa.tools.testraill.models.cases.Links;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "offset",
        "limit",
        "size",
        "_links",
        "history"
})
public class CaseHistory {
    @JsonProperty("offset")
    private Integer offset;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("_links")
    private Links links;
    @JsonProperty("history")
    private List<Item> history;
}
