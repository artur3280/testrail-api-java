package qa.tools.testraill.models.cases;

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
        "content",
        "additional_info",
        "expected",
        "refs",
        "shared_step_id"
})
public class CustomStepsSeparated {
    @JsonProperty("content")
    private String content;
    @JsonProperty("additional_info")
    private String additionalInfo;
    @JsonProperty("expected")
    private String expected;
    @JsonProperty("refs")
    private String refs;
    @JsonProperty("shared_step_id")
    private Integer sharedStepId;
}
