package qa.tools.testraill.models.results;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import lombok.*;
import qa.tools.testraill.core.internal.CsvToListDeserializer;
import qa.tools.testraill.core.internal.ListToCsvSerializer;
import qa.tools.testraill.requests.Projects;
import qa.tools.testraill.requests.Results;

import java.io.IOException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "test_id",
        "created_on",
        "name",
        "announcement",
        "show_announcement",
        "is_completed",
        "completed_on",
        "suite_mode",
        "created_by",
        "custom_step_results",
        "custom_step_results",
        "url"
})
public class Result {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("test_id")
    private Integer testId;
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
    private Long completedOn;
    @JsonProperty("created_on")
    private Long createdOn;
    @JsonProperty("suite_mode")
    @JsonView({Projects.Add.class, Projects.Update.class})
    private Integer suiteMode;
    @JsonProperty("url")
    private String url;
    @JsonProperty("created_by")
    private Integer createdBy;
    @JsonProperty("attachment_ids")
    private List<Integer> attachment_ids;

    /*
    * For requests
    * */
    private static final String CUSTOM_FIELD_KEY_PREFIX = "custom_";
    @JsonProperty("status_id")
    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    private Integer statusId;

    @JsonProperty("case_id")
    @JsonView({Results.AddListForCases.class})
    private Integer caseId;

    @JsonProperty("comment")
    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    private String comment;

    @JsonProperty("version")
    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    private String version;

    @JsonProperty("elapsed")
    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    private String elapsed;

    @JsonProperty("defects")
    @JsonSerialize(using = ListToCsvSerializer.class)
    @JsonDeserialize(using = CsvToListDeserializer.class)
    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    private List<String> defects;

    @JsonProperty("assignedto_id")
    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    private Integer assignedToId;

    @JsonView({
            Results.Add.class,
            Results.AddForCase.class,
            Results.AddList.class,
            Results.AddListForCases.class})
    @JsonIgnore
    @JsonProperty("custom_step_results")
    private Map<String, Object> customFields;

    public Result addDefect(@NonNull String defect) {
        if (defect == null) {
            throw new NullPointerException("defect");
        }
        Preconditions.checkArgument(!defect.isEmpty(), "defect cannot be empty");
        java.util.List<String> defects = getDefects();
        if (defects == null) {
            defects = new ArrayList<>();
        }
        defects.add(defect);
        setDefects(defects);
        return this;
    }

    @JsonAnyGetter
    @JsonSerialize(keyUsing = CustomFieldSerializer.class)
    public Map<String, Object> getCustomFields() {
        return MoreObjects.firstNonNull(customFields, Collections.<String, Object>emptyMap());
    }

    private static class CustomFieldSerializer extends StdKeySerializers.StringKeySerializer  {
        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            super.serialize(CUSTOM_FIELD_KEY_PREFIX + o, jsonGenerator, serializerProvider);
        }
    }

    @JsonAnySetter
    private void addUnknownField(String key, Object value) {
        if (key.startsWith(CUSTOM_FIELD_KEY_PREFIX)) {
            addCustomField(key, value);
        }
    }

    public Result addCustomField(String key, Object value) {
        if (customFields == null) {
            customFields = new HashMap<>();
        }
        customFields.put(key.replaceFirst(CUSTOM_FIELD_KEY_PREFIX, ""), value);
        return this;
    }

    public <T> T getCustomField(String key) {
        return (T)getCustomFields().get(key);
    }
}
