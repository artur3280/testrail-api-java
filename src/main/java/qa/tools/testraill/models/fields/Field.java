package qa.tools.testraill.models.fields;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import qa.tools.testraill.core.internal.IntToBooleanDeserializer;
import qa.tools.testraill.core.internal.StringToMapDeserializer;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Field {
    private int id;
    private String label;
    private String name;
    private String description;
    private String systemName;
    private int typeId;
    private Type type;
    private int displayOrder;
    private List<Config> configs;

    /**
     * TestRail type of field.
     * Map of TestRail field types to their corresponding Java types:
     * STRING -- java.lang.String
     * INTEGER -- java.lang.Integer
     * TEXT -- java.lang.String
     * URL -- java.lang.String
     * CHECKBOX -- java.lang.Boolean
     * DROPDOWN -- java.lang.String
     * USER -- java.lang.Integer
     * DATE -- java.lang.String
     * MILESTONE -- java.lang.Integer
     * STEPS
     * STEP_RESULTS
     * MULTI_SELECT
     */
    public static enum Type {
        UNKNOWN(Config.Options.class, new TypeReference<Object>() {

        }),
        STRING(Config.StringOptions.class, new TypeReference<String>() {

        }),
        INTEGER(Config.IntegerOptions.class, new TypeReference<Integer>() {

        }),
        TEXT(Config.TextOptions.class, new TypeReference<String>() {

        }),
        URL(Config.UrlOptions.class, new TypeReference<String>() {

        }),
        CHECKBOX(Config.CheckboxOptions.class, new TypeReference<Boolean>() {

        }),
        DROPDOWN(Config.DropdownOptions.class, new TypeReference<String>() {

        }),
        USER(Config.UserOptions.class, new TypeReference<Integer>() {

        }),
        DATE(Config.DateOptions.class, new TypeReference<String>() {

        }),
        MILESTONE(Config.MilestoneOptions.class, new TypeReference<Integer>() {

        }),
        STEPS(Config.StepsOptions.class, new TypeReference<List<Step>>() {

        }),
        STEP_RESULTS(Config.StepResultsOptions.class, new TypeReference<List<StepResult>>() {

        }),
        MULTI_SELECT(Config.MultiSelectOptions.class, new TypeReference<List<String>>() {

        });
        private final Class<? extends Config.Options> optionsClass;
        private final TypeReference<?> typeReference;

        public static Type getType(int typeId) {
            return typeId >= 0 && typeId < Type.values().length ? Type.values()[typeId] : UNKNOWN;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        private Type(final Class<? extends Config.Options> optionsClass, final TypeReference<?> typeReference) {
            this.optionsClass = optionsClass;
            this.typeReference = typeReference;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Class<? extends Config.Options> getOptionsClass() {
            return this.optionsClass;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public TypeReference<?> getTypeReference() {
            return this.typeReference;
        }
    }

    /**
     * Configuration for a {@code Field}.
     */
    public static class Config {
        private String id;
        private Context context;
        private Options options;

        /**
         * Options for a {@code Field} configuration.
         */
        public static class Options {
            @JsonProperty
            private boolean isRequired;
            private Map<String, Object> unknownFields;

            @JsonAnySetter
            private Options addUnknownField(String key, Object value) {
                if (unknownFields == null) {
                    unknownFields = new HashMap<>();
                }
                unknownFields.put(key, value);
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Options() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Options setRequired(final boolean isRequired) {
                this.isRequired = isRequired;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.Options)) return false;
                final Options other = (Options) o;
                if (!other.canEqual((Object) this)) return false;
                if (this.isRequired() != other.isRequired()) return false;
                final Object this$unknownFields = this.getUnknownFields();
                final Object other$unknownFields = other.getUnknownFields();
                if (this$unknownFields == null ? other$unknownFields != null : !this$unknownFields.equals(other$unknownFields))
                    return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.Options;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + (this.isRequired() ? 79 : 97);
                final Object $unknownFields = this.getUnknownFields();
                result = result * PRIME + ($unknownFields == null ? 0 : $unknownFields.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.Options(isRequired=" + this.isRequired() + ", unknownFields=" + this.getUnknownFields() + ")";
            }

            @JsonIgnore
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean isRequired() {
                return this.isRequired;
            }

            @JsonAnyGetter
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            private Map<String, Object> getUnknownFields() {
                return this.unknownFields;
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#STRING}.
         */
        public static class StringOptions extends Options {
            private String defaultValue;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StringOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StringOptions setDefaultValue(final String defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.StringOptions)) return false;
                final StringOptions other = (StringOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$defaultValue = this.getDefaultValue();
                final Object other$defaultValue = other.getDefaultValue();
                if (this$defaultValue == null ? other$defaultValue != null : !this$defaultValue.equals(other$defaultValue))
                    return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.StringOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $defaultValue = this.getDefaultValue();
                result = result * PRIME + ($defaultValue == null ? 0 : $defaultValue.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.StringOptions(super=" + super.toString() + ", defaultValue=" + this.getDefaultValue() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#INTEGER}.
         */
        public static class IntegerOptions extends Options {
            private BigInteger defaultValue;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public IntegerOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public BigInteger getDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public IntegerOptions setDefaultValue(final BigInteger defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.IntegerOptions)) return false;
                final IntegerOptions other = (IntegerOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$defaultValue = this.getDefaultValue();
                final Object other$defaultValue = other.getDefaultValue();
                if (this$defaultValue == null ? other$defaultValue != null : !this$defaultValue.equals(other$defaultValue))
                    return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.IntegerOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $defaultValue = this.getDefaultValue();
                result = result * PRIME + ($defaultValue == null ? 0 : $defaultValue.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.IntegerOptions(super=" + super.toString() + ", defaultValue=" + this.getDefaultValue() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#TEXT}.
         */
        public static class TextOptions extends Options {
            private String defaultValue;
            private String format;
            private int rows;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public TextOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getFormat() {
                return this.format;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int getRows() {
                return this.rows;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public TextOptions setDefaultValue(final String defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public TextOptions setFormat(final String format) {
                this.format = format;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public TextOptions setRows(final int rows) {
                this.rows = rows;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.TextOptions)) return false;
                final TextOptions other = (TextOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$defaultValue = this.getDefaultValue();
                final Object other$defaultValue = other.getDefaultValue();
                if (this$defaultValue == null ? other$defaultValue != null : !this$defaultValue.equals(other$defaultValue))
                    return false;
                final Object this$format = this.getFormat();
                final Object other$format = other.getFormat();
                if (this$format == null ? other$format != null : !this$format.equals(other$format)) return false;
                if (this.getRows() != other.getRows()) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.TextOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $defaultValue = this.getDefaultValue();
                result = result * PRIME + ($defaultValue == null ? 0 : $defaultValue.hashCode());
                final Object $format = this.getFormat();
                result = result * PRIME + ($format == null ? 0 : $format.hashCode());
                result = result * PRIME + this.getRows();
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.TextOptions(super=" + super.toString() + ", defaultValue=" + this.getDefaultValue() + ", format=" + this.getFormat() + ", rows=" + this.getRows() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#URL}.
         */
        public static class UrlOptions extends Options {
            private String defaultValue;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public UrlOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public UrlOptions setDefaultValue(final String defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.UrlOptions)) return false;
                final UrlOptions other = (UrlOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$defaultValue = this.getDefaultValue();
                final Object other$defaultValue = other.getDefaultValue();
                if (this$defaultValue == null ? other$defaultValue != null : !this$defaultValue.equals(other$defaultValue))
                    return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.UrlOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $defaultValue = this.getDefaultValue();
                result = result * PRIME + ($defaultValue == null ? 0 : $defaultValue.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.UrlOptions(super=" + super.toString() + ", defaultValue=" + this.getDefaultValue() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#CHECKBOX}.
         */
        public static class CheckboxOptions extends Options {
            @JsonDeserialize(using = IntToBooleanDeserializer.class)
            private boolean defaultValue;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public CheckboxOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean isDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public CheckboxOptions setDefaultValue(final boolean defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.CheckboxOptions)) return false;
                final CheckboxOptions other = (CheckboxOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                if (this.isDefaultValue() != other.isDefaultValue()) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.CheckboxOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                result = result * PRIME + (this.isDefaultValue() ? 79 : 97);
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.CheckboxOptions(super=" + super.toString() + ", defaultValue=" + this.isDefaultValue() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#DROPDOWN}.
         */
        public static class DropdownOptions extends Options {
            private String defaultValue;
            @JsonDeserialize(using = StringToMapDeserializer.class)
            private Map<String, String> items;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public DropdownOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Map<String, String> getItems() {
                return this.items;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public DropdownOptions setDefaultValue(final String defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public DropdownOptions setItems(final Map<String, String> items) {
                this.items = items;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.DropdownOptions)) return false;
                final DropdownOptions other = (DropdownOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$defaultValue = this.getDefaultValue();
                final Object other$defaultValue = other.getDefaultValue();
                if (this$defaultValue == null ? other$defaultValue != null : !this$defaultValue.equals(other$defaultValue))
                    return false;
                final Object this$items = this.getItems();
                final Object other$items = other.getItems();
                if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.DropdownOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $defaultValue = this.getDefaultValue();
                result = result * PRIME + ($defaultValue == null ? 0 : $defaultValue.hashCode());
                final Object $items = this.getItems();
                result = result * PRIME + ($items == null ? 0 : $items.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.DropdownOptions(super=" + super.toString() + ", defaultValue=" + this.getDefaultValue() + ", items=" + this.getItems() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#USER}.
         */
        public static class UserOptions extends Options {
            private int defaultValue;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public UserOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int getDefaultValue() {
                return this.defaultValue;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public UserOptions setDefaultValue(final int defaultValue) {
                this.defaultValue = defaultValue;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.UserOptions)) return false;
                final UserOptions other = (UserOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                if (this.getDefaultValue() != other.getDefaultValue()) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.UserOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                result = result * PRIME + this.getDefaultValue();
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.UserOptions(super=" + super.toString() + ", defaultValue=" + this.getDefaultValue() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#DATE}.
         */
        public static class DateOptions extends Options {

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public DateOptions() {
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.DateOptions)) return false;
                final DateOptions other = (DateOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.DateOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.DateOptions(super=" + super.toString() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#MILESTONE}.
         */
        public static class MilestoneOptions extends Options {

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public MilestoneOptions() {
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.MilestoneOptions)) return false;
                final MilestoneOptions other = (MilestoneOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.MilestoneOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.MilestoneOptions(super=" + super.toString() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#STEPS}.
         */
        public static class StepsOptions extends Options {
            private String format;
            private boolean hasExpected;
            private int rows;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepsOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getFormat() {
                return this.format;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean isHasExpected() {
                return this.hasExpected;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int getRows() {
                return this.rows;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepsOptions setFormat(final String format) {
                this.format = format;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepsOptions setHasExpected(final boolean hasExpected) {
                this.hasExpected = hasExpected;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepsOptions setRows(final int rows) {
                this.rows = rows;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.StepsOptions)) return false;
                final StepsOptions other = (StepsOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$format = this.getFormat();
                final Object other$format = other.getFormat();
                if (this$format == null ? other$format != null : !this$format.equals(other$format)) return false;
                if (this.isHasExpected() != other.isHasExpected()) return false;
                if (this.getRows() != other.getRows()) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.StepsOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $format = this.getFormat();
                result = result * PRIME + ($format == null ? 0 : $format.hashCode());
                result = result * PRIME + (this.isHasExpected() ? 79 : 97);
                result = result * PRIME + this.getRows();
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.StepsOptions(super=" + super.toString() + ", format=" + this.getFormat() + ", hasExpected=" + this.isHasExpected() + ", rows=" + this.getRows() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#STEP_RESULTS}.
         */
        public static class StepResultsOptions extends Options {
            private String format;
            private boolean hasExpected;
            private boolean hasActual;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepResultsOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String getFormat() {
                return this.format;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean isHasExpected() {
                return this.hasExpected;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean isHasActual() {
                return this.hasActual;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepResultsOptions setFormat(final String format) {
                this.format = format;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepResultsOptions setHasExpected(final boolean hasExpected) {
                this.hasExpected = hasExpected;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public StepResultsOptions setHasActual(final boolean hasActual) {
                this.hasActual = hasActual;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.StepResultsOptions)) return false;
                final StepResultsOptions other = (StepResultsOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$format = this.getFormat();
                final Object other$format = other.getFormat();
                if (this$format == null ? other$format != null : !this$format.equals(other$format)) return false;
                if (this.isHasExpected() != other.isHasExpected()) return false;
                if (this.isHasActual() != other.isHasActual()) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.StepResultsOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $format = this.getFormat();
                result = result * PRIME + ($format == null ? 0 : $format.hashCode());
                result = result * PRIME + (this.isHasExpected() ? 79 : 97);
                result = result * PRIME + (this.isHasActual() ? 79 : 97);
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.StepResultsOptions(super=" + super.toString() + ", format=" + this.getFormat() + ", hasExpected=" + this.isHasExpected() + ", hasActual=" + this.isHasActual() + ")";
            }
        }

        /**
         * Options for a {@code Field} of type {@link Type#MULTI_SELECT}.
         */
        public static class MultiSelectOptions extends Options {
            @JsonDeserialize(using = StringToMapDeserializer.class)
            private Map<String, String> items;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public MultiSelectOptions() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Map<String, String> getItems() {
                return this.items;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public MultiSelectOptions setItems(final Map<String, String> items) {
                this.items = items;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.MultiSelectOptions)) return false;
                final MultiSelectOptions other = (MultiSelectOptions) o;
                if (!other.canEqual((Object) this)) return false;
                if (!super.equals(o)) return false;
                final Object this$items = this.getItems();
                final Object other$items = other.getItems();
                if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.MultiSelectOptions;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + super.hashCode();
                final Object $items = this.getItems();
                result = result * PRIME + ($items == null ? 0 : $items.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.MultiSelectOptions(super=" + super.toString() + ", items=" + this.getItems() + ")";
            }
        }

        public static class Context {
            @JsonProperty
            private boolean isGlobal;
            private List<Integer> projectIds;

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Context() {
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public List<Integer> getProjectIds() {
                return this.projectIds;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Context setGlobal(final boolean isGlobal) {
                this.isGlobal = isGlobal;
                return this;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public Context setProjectIds(final List<Integer> projectIds) {
                this.projectIds = projectIds;
                return this;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean equals(final Object o) {
                if (o == this) return true;
                if (!(o instanceof Field.Config.Context)) return false;
                final Context other = (Context) o;
                if (!other.canEqual((Object) this)) return false;
                if (this.isGlobal() != other.isGlobal()) return false;
                final Object this$projectIds = this.getProjectIds();
                final Object other$projectIds = other.getProjectIds();
                if (this$projectIds == null ? other$projectIds != null : !this$projectIds.equals(other$projectIds))
                    return false;
                return true;
            }

            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            protected boolean canEqual(final Object other) {
                return other instanceof Field.Config.Context;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public int hashCode() {
                final int PRIME = 59;
                int result = 1;
                result = result * PRIME + (this.isGlobal() ? 79 : 97);
                final Object $projectIds = this.getProjectIds();
                result = result * PRIME + ($projectIds == null ? 0 : $projectIds.hashCode());
                return result;
            }

            @Override
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public String toString() {
                return "Field.Config.Context(isGlobal=" + this.isGlobal() + ", projectIds=" + this.getProjectIds() + ")";
            }

            @JsonIgnore
            @SuppressWarnings("all")
            @javax.annotation.Generated("lombok")
            public boolean isGlobal() {
                return this.isGlobal;
            }
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Config() {
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String getId() {
            return this.id;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Context getContext() {
            return this.context;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Options getOptions() {
            return this.options;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Config setId(final String id) {
            this.id = id;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Config setContext(final Context context) {
            this.context = context;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Config setOptions(final Options options) {
            this.options = options;
            return this;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Field.Config)) return false;
            final Config other = (Config) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$id = this.getId();
            final Object other$id = other.getId();
            if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
            final Object this$context = this.getContext();
            final Object other$context = other.getContext();
            if (this$context == null ? other$context != null : !this$context.equals(other$context)) return false;
            final Object this$options = this.getOptions();
            final Object other$options = other.getOptions();
            if (this$options == null ? other$options != null : !this$options.equals(other$options)) return false;
            return true;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        protected boolean canEqual(final Object other) {
            return other instanceof Field.Config;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $id = this.getId();
            result = result * PRIME + ($id == null ? 0 : $id.hashCode());
            final Object $context = this.getContext();
            result = result * PRIME + ($context == null ? 0 : $context.hashCode());
            final Object $options = this.getOptions();
            result = result * PRIME + ($options == null ? 0 : $options.hashCode());
            return result;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String toString() {
            return "Field.Config(id=" + this.getId() + ", context=" + this.getContext() + ", options=" + this.getOptions() + ")";
        }
    }

    /**
     * Step; a custom field type.
     */
    public static class Step {
        private String content;
        private String expected;

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Step() {
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String getContent() {
            return this.content;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String getExpected() {
            return this.expected;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Step setContent(final String content) {
            this.content = content;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Step setExpected(final String expected) {
            this.expected = expected;
            return this;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Field.Step)) return false;
            final Step other = (Step) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$content = this.getContent();
            final Object other$content = other.getContent();
            if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
            final Object this$expected = this.getExpected();
            final Object other$expected = other.getExpected();
            if (this$expected == null ? other$expected != null : !this$expected.equals(other$expected)) return false;
            return true;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        protected boolean canEqual(final Object other) {
            return other instanceof Field.Step;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $content = this.getContent();
            result = result * PRIME + ($content == null ? 0 : $content.hashCode());
            final Object $expected = this.getExpected();
            result = result * PRIME + ($expected == null ? 0 : $expected.hashCode());
            return result;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String toString() {
            return "Field.Step(content=" + this.getContent() + ", expected=" + this.getExpected() + ")";
        }
    }

    /**
     * Step result; a custom field type.
     */
    public static class StepResult {
        private String content;
        private String expected;
        private String actual;
        private Integer statusId;

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public StepResult() {
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String getContent() {
            return this.content;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String getExpected() {
            return this.expected;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String getActual() {
            return this.actual;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public Integer getStatusId() {
            return this.statusId;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public StepResult setContent(final String content) {
            this.content = content;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public StepResult setExpected(final String expected) {
            this.expected = expected;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public StepResult setActual(final String actual) {
            this.actual = actual;
            return this;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public StepResult setStatusId(final Integer statusId) {
            this.statusId = statusId;
            return this;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof Field.StepResult)) return false;
            final StepResult other = (StepResult) o;
            if (!other.canEqual((Object) this)) return false;
            final Object this$content = this.getContent();
            final Object other$content = other.getContent();
            if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
            final Object this$expected = this.getExpected();
            final Object other$expected = other.getExpected();
            if (this$expected == null ? other$expected != null : !this$expected.equals(other$expected)) return false;
            final Object this$actual = this.getActual();
            final Object other$actual = other.getActual();
            if (this$actual == null ? other$actual != null : !this$actual.equals(other$actual)) return false;
            final Object this$statusId = this.getStatusId();
            final Object other$statusId = other.getStatusId();
            if (this$statusId == null ? other$statusId != null : !this$statusId.equals(other$statusId)) return false;
            return true;
        }

        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        protected boolean canEqual(final Object other) {
            return other instanceof Field.StepResult;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final Object $content = this.getContent();
            result = result * PRIME + ($content == null ? 0 : $content.hashCode());
            final Object $expected = this.getExpected();
            result = result * PRIME + ($expected == null ? 0 : $expected.hashCode());
            final Object $actual = this.getActual();
            result = result * PRIME + ($actual == null ? 0 : $actual.hashCode());
            final Object $statusId = this.getStatusId();
            result = result * PRIME + ($statusId == null ? 0 : $statusId.hashCode());
            return result;
        }

        @Override
        @SuppressWarnings("all")
        @javax.annotation.Generated("lombok")
        public String toString() {
            return "Field.StepResult(content=" + this.getContent() + ", expected=" + this.getExpected() + ", actual=" + this.getActual() + ", statusId=" + this.getStatusId() + ")";
        }
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field() {
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int getId() {
        return this.id;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getLabel() {
        return this.label;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getName() {
        return this.name;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getDescription() {
        return this.description;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String getSystemName() {
        return this.systemName;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int getTypeId() {
        return this.typeId;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Type getType() {
        return this.type;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int getDisplayOrder() {
        return this.displayOrder;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public List<Config> getConfigs() {
        return this.configs;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setId(final int id) {
        this.id = id;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setLabel(final String label) {
        this.label = label;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setName(final String name) {
        this.name = name;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setDescription(final String description) {
        this.description = description;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setSystemName(final String systemName) {
        this.systemName = systemName;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setTypeId(final int typeId) {
        this.typeId = typeId;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setType(final Type type) {
        this.type = type;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setDisplayOrder(final int displayOrder) {
        this.displayOrder = displayOrder;
        return this;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public Field setConfigs(final List<Config> configs) {
        this.configs = configs;
        return this;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Field)) return false;
        final Field other = (Field) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        final Object this$label = this.getLabel();
        final Object other$label = other.getLabel();
        if (this$label == null ? other$label != null : !this$label.equals(other$label)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$systemName = this.getSystemName();
        final Object other$systemName = other.getSystemName();
        if (this$systemName == null ? other$systemName != null : !this$systemName.equals(other$systemName))
            return false;
        if (this.getTypeId() != other.getTypeId()) return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        if (this.getDisplayOrder() != other.getDisplayOrder()) return false;
        final Object this$configs = this.getConfigs();
        final Object other$configs = other.getConfigs();
        if (this$configs == null ? other$configs != null : !this$configs.equals(other$configs)) return false;
        return true;
    }

    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    protected boolean canEqual(final Object other) {
        return other instanceof Field;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        final Object $label = this.getLabel();
        result = result * PRIME + ($label == null ? 0 : $label.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 0 : $name.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 0 : $description.hashCode());
        final Object $systemName = this.getSystemName();
        result = result * PRIME + ($systemName == null ? 0 : $systemName.hashCode());
        result = result * PRIME + this.getTypeId();
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 0 : $type.hashCode());
        result = result * PRIME + this.getDisplayOrder();
        final Object $configs = this.getConfigs();
        result = result * PRIME + ($configs == null ? 0 : $configs.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    @javax.annotation.Generated("lombok")
    public String toString() {
        return "Field(id=" + this.getId() + ", label=" + this.getLabel() + ", name=" + this.getName() + ", description=" + this.getDescription() + ", systemName=" + this.getSystemName() + ", typeId=" + this.getTypeId() + ", type=" + this.getType() + ", displayOrder=" + this.getDisplayOrder() + ", configs=" + this.getConfigs() + ")";
    }
}
