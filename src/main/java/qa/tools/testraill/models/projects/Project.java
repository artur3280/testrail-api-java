package qa.tools.testraill.models.projects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import qa.tools.testraill.requests.Projects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "announcement",
        "show_announcement",
        "is_completed",
        "completed_on",
        "suite_mode",
        "url"
})
public class Project {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("name")
    @JsonView({Projects.Add.class, Projects.Update.class})
    private String name;
    @JsonProperty("announcement")
    @JsonView({Projects.Add.class, Projects.Update.class})
    private String announcement;
    @JsonProperty("show_announcement")
    @JsonView({Projects.Add.class, Projects.Update.class})
    private Boolean showAnnouncement;
    @JsonProperty("is_completed")
    private Boolean isCompleted;
    @JsonProperty("completed_on")
    private Object completedOn;
    @JsonProperty("suite_mode")
    @JsonView({Projects.Add.class, Projects.Update.class})
    private Integer suiteMode;
    @JsonProperty("url")
    private String url;
}
