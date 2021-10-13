package qa.tools.testraill.requests;

import lombok.NonNull;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.fields.ResultField;
import qa.tools.testraill.models.results.Result;
import qa.tools.testraill.models.results.ResultsList;

import static com.google.common.base.Preconditions.checkArgument;

public class Results {
    public List list(@NonNull Integer testId, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(testId > 0, "testId should be positive");
        return new List(testId, resultFields);
    }

    public ListForCase listForCase(@NonNull Integer runId, @NonNull Integer testId, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(testId > 0, "testId should be positive");
        checkArgument(runId > 0, "runId should be positive");
        return new ListForCase(runId, testId, resultFields);
    }

    public ListForRun listForRun(@NonNull Integer runId, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(runId > 0, "runId should be positive");
        return new ListForRun(runId, resultFields);
    }

    public Add add(@NonNull Integer testId, @NonNull Result result, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(testId > 0, "testId should be positive");
        return new Add(testId, result, resultFields);
    }

    public AddForCase addForCase(@NonNull Integer runId, @NonNull Integer testCaseId, @NonNull Result result, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(runId > 0, "runId should be positive");
        checkArgument(testCaseId > 0, "testCaseId should be positive");
        return new AddForCase(runId, testCaseId, result, resultFields);
    }

    public AddList add(@NonNull Integer runId, @NonNull ResultsList results, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(runId > 0, "runId should be positive");
        checkArgument(!results.getResults().isEmpty(), "results cannot be empty");
        return new AddList(runId, results, resultFields);
    }

    public AddListForCases addForCases(@NonNull Integer runId, @NonNull ResultsList results, @NonNull java.util.List<ResultField> resultFields) {
        checkArgument(runId > 0, "runId should be positive");
        checkArgument(!results.getResults().isEmpty(), "results cannot be empty");
        return new AddListForCases(runId, results, resultFields);
    }

    public class List extends Requester<ResultsList> {
        private static final String REST_PATH = "get_results/%s";
        private java.util.List<ResultField> resultFields;

        public List(@NonNull Integer testId, java.util.List<ResultField> resultFields) {
            super("GET", String.format(REST_PATH, testId), ResultsList.class);
            this.resultFields = resultFields;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }
    }

    public class ListForRun extends Requester<ResultsList> {
        private static final String REST_PATH = "get_results_for_run/%s";
        private java.util.List<ResultField> resultFields;

        public ListForRun(@NonNull Integer runId, java.util.List<ResultField> resultFields) {
            super("GET", String.format(REST_PATH, runId), ResultsList.class);
            this.resultFields = resultFields;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }
    }

    public class ListForCase extends Requester<ResultsList> {
        private static final String REST_PATH = "get_results_for_case/%s/%s";
        private final java.util.List<ResultField> resultFields;

        public ListForCase(@NonNull Integer runId, @NonNull Integer testId, java.util.List<ResultField> resultFields) {
            super("GET", String.format(REST_PATH, runId, testId), ResultsList.class);
            this.resultFields = resultFields;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }
    }

    public class Add extends Requester<Result> {
        private static final String REST_PATH = "add_result/%s";
        private final java.util.List<ResultField> resultFields;
        private final Result result;

        public Add(@NonNull Integer testId, @NonNull Result result, java.util.List<ResultField> resultFields) {
            super("POST", String.format(REST_PATH, testId), Result.class);
            this.resultFields = resultFields;
            this.result = result;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }

        @Override
        public Object getContent() {
            return result;
        }
    }

    public class AddForCase extends Requester<Result> {
        private static final String REST_PATH = "add_result_for_case/%s/%s";
        private final java.util.List<ResultField> resultFields;
        private final Result result;

        public AddForCase(@NonNull Integer runId, @NonNull Integer testId, @NonNull Result result, java.util.List<ResultField> resultFields) {
            super("POST", String.format(REST_PATH, runId, testId), Result.class);
            this.resultFields = resultFields;
            this.result = result;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }

        @Override
        public Object getContent() {
            return result;
        }
    }

    public class AddList extends Requester<ResultsList> {
        private static final String REST_PATH = "add_results/%s";
        private final java.util.List<ResultField> resultFields;
        private final ResultsList result;

        public AddList(@NonNull Integer runId, @NonNull ResultsList result, java.util.List<ResultField> resultFields) {
            super("POST", String.format(REST_PATH, runId), ResultsList.class);
            this.resultFields = resultFields;
            this.result = result;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }

        @Override
        public Object getContent() {
            return result;
        }
    }

    public class AddListForCases extends Requester<ResultsList> {
        private static final String REST_PATH = "add_results_for_cases/%s";
        private final java.util.List<ResultField> resultFields;
        private final ResultsList result;

        public AddListForCases(@NonNull Integer runId, @NonNull ResultsList result, java.util.List<ResultField> resultFields) {
            super("POST", String.format(REST_PATH, runId), ResultsList.class);
            this.resultFields = resultFields;
            this.result = result;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return resultFields;
        }

        @Override
        public Object getContent() {
            return result;
        }
    }

}
