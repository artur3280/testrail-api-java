package qa.tools.testraill.models.runs;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import qa.tools.testraill.core.internal.ListToCsvSerializer;
import qa.tools.testraill.requests.Runs;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "assignedto_id",
        "blocked_count",
        "completed_on",
        "config",
        "config_ids",
        "created_by",
        "created_on",
        "refs",
        "custom_status1_count",
        "custom_status2_count",
        "custom_status3_count",
        "custom_status4_count",
        "custom_status5_count",
        "custom_status6_count",
        "custom_status7_count",
        "description",
        "failed_count",
        "id",
        "include_all",
        "is_completed",
        "milestone_id",
        "name",
        "passed_count",
        "plan_id",
        "project_id",
        "retest_count",
        "suite_id",
        "untested_count",
        "updated_on",
        "url"
})
public class Run {
    @JsonProperty("assignedto_id")
    @JsonView({Runs.Add.class, Runs.Update.class})
    private Integer assignedtoId;
    @JsonProperty("blocked_count")
    private Integer blockedCount;
    @JsonProperty("completed_on")
    private Object completedOn;
    @JsonProperty("config")
    private String config;
    @JsonProperty("config_ids")
    private List<Integer> configIds = null;
    @JsonProperty("created_by")
    private Integer createdBy;
    @JsonProperty("created_on")
    private Integer createdOn;
    @JsonProperty("refs")
    private String refs;
    @JsonProperty("custom_status1_count")
    private Integer customStatus1Count;
    @JsonProperty("custom_status2_count")
    private Integer customStatus2Count;
    @JsonProperty("custom_status3_count")
    private Integer customStatus3Count;
    @JsonProperty("custom_status4_count")
    private Integer customStatus4Count;
    @JsonProperty("custom_status5_count")
    private Integer customStatus5Count;
    @JsonProperty("custom_status6_count")
    private Integer customStatus6Count;
    @JsonProperty("custom_status7_count")
    private Integer customStatus7Count;
    @JsonProperty("description")
    @JsonView({Runs.Add.class, Runs.Update.class})
    private Object description;
    @JsonProperty("failed_count")
    private Integer failedCount;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("include_all")
    @JsonView({Runs.Add.class, Runs.Update.class})
    private Boolean includeAll;
    @JsonProperty("is_completed")
    private Boolean isCompleted;
    @JsonProperty("milestone_id")
    @JsonView({Runs.Add.class, Runs.Update.class})
    private Integer milestoneId;
    @JsonProperty("name")
    @JsonView({Runs.Add.class, Runs.Update.class})
    private String name;
    @JsonProperty("passed_count")
    private Integer passedCount;
    @JsonProperty("plan_id")
    private Integer planId;
    @JsonProperty("project_id")
    private Integer projectId;
    @JsonProperty("retest_count")
    private Integer retestCount;
    @JsonProperty("suite_id")
    @JsonView({Runs.Add.class, Runs.Update.class})
    private Integer suiteId;
    @JsonProperty("untested_count")
    private Integer untestedCount;
    @JsonProperty("updated_on")
    private Object updatedOn;
    @JsonProperty("url")
    private String url;
    @JsonProperty("case_ids")
    @JsonView({Runs.Add.class, Runs.Update.class})
    @JsonSerialize(using = ListToCsvSerializer.class)
    private ArrayList<Integer> caseIds;
}
