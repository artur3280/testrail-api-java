package qa.tools.testraill.models.cases;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializer;
import com.google.common.base.MoreObjects;
import lombok.*;
import qa.tools.testraill.core.internal.ListToCsvSerializer;
import qa.tools.testraill.requests.Cases;

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
        "created_by",
        "created_on",
        "custom_expected",
        "custom_preconds",
        "custom_steps",
        "custom_steps_separated",
        "estimate",
        "estimate_forecast",
        "id",
        "milestone_id",
        "priority_id",
        "refs",
        "section_id",
        "suite_id",
        "title",
        "type_id",
        "updated_by",
        "updated_on"
})
public class Case {
    private static final String CUSTOM_FIELD_KEY_PREFIX = "custom_";
    @JsonProperty("created_by")
    @JsonSerialize(using = ListToCsvSerializer.class)
    private java.util.List<Integer> createdBy;
    @JsonProperty("created_on")
    private Integer createdOn;
    @JsonProperty("custom_expected")
    private String customExpected;
    @JsonProperty("custom_preconds")
    private String customPreconds;
    @JsonProperty("custom_steps")
    private String customSteps;
    @JsonProperty("custom_steps_separated")
    private List<CustomStepsSeparated> customStepsSeparated = null;
    @JsonProperty("estimate")
    @JsonView({Cases.Add.class, Cases.Update.class})
    private String estimate;
    @JsonProperty("estimate_forecast")
    private Object estimateForecast;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("milestone_id")
    @JsonSerialize(using = ListToCsvSerializer.class)
    @JsonView({Cases.Add.class, Cases.Update.class})
    private java.util.List<Integer> milestoneId;
    @JsonProperty("priority_id")
    @JsonSerialize(using = ListToCsvSerializer.class)
    @JsonView({Cases.Add.class, Cases.Update.class})
    private List<Integer> priorityId;
    @JsonProperty("refs")
    @JsonView({Cases.Add.class, Cases.Update.class})
    private String refs;
    @JsonProperty("section_id")
    private Integer sectionId;
    @JsonProperty("suite_id")
    private Integer suiteId;
    @JsonProperty("title")
    @JsonView({Cases.Add.class, Cases.Update.class})
    private String title;
    @JsonProperty("type_id")
    @JsonSerialize(using = ListToCsvSerializer.class)
    @JsonView({Cases.Add.class, Cases.Update.class})
    private List<Integer> typeId;
    @JsonProperty("updated_by")
    @JsonSerialize(using = ListToCsvSerializer.class)
    private List<Integer> updatedBy;
    @JsonProperty("updated_on")
    private Integer updatedOn;
    @JsonProperty("template_id")
    @JsonView({Cases.Add.class, Cases.Update.class})
    private Integer templateId;

    @JsonView({Cases.Add.class, Cases.Update.class})
    @JsonIgnore
    private Map<String, Object> customFields;
    @JsonAnyGetter
    @JsonSerialize(keyUsing = CustomFieldSerializer.class)
    public Map<String, Object> getCustomFields() {
        return MoreObjects.firstNonNull(customFields, Collections.<String, Object>emptyMap());
    }

    public Case addCustomField(String key, Object value) {
        if (customFields == null) {
            customFields = new HashMap<>();
        }
        customFields.put(key.replaceFirst(CUSTOM_FIELD_KEY_PREFIX, ""), value);
        return this;
    }

    /**
     * Support for forward compatibility and extracting custom fields.
     *
     * @param key the name of the unknown field
     * @param value the value of the unkown field
     */
    @JsonAnySetter
    private void addUnknownField(String key, Object value) {
        if (key.startsWith(CUSTOM_FIELD_KEY_PREFIX)) {
            addCustomField(key, value);
        }
    }

    public <T> T getCustomField(String key) {
        return (T)getCustomFields().get(key);
    }

    /**
     * Serializer for custom fields.
     */
    private static class CustomFieldSerializer extends StdKeySerializer {


        @Override
        public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
            super.serialize(CUSTOM_FIELD_KEY_PREFIX + o, jsonGenerator, serializerProvider);
        }
    }
}
