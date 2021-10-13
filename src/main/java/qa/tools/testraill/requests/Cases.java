package qa.tools.testraill.requests;

import lombok.NonNull;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.case_history.CaseHistory;
import qa.tools.testraill.models.cases.Case;
import qa.tools.testraill.models.cases.CasesList;
import qa.tools.testraill.models.fields.CaseField;

import static com.google.common.base.Preconditions.checkArgument;

public class Cases {

    public History history(@NonNull Integer caseId) {
        checkArgument(caseId >= 0, "Case ID must be greater than zero");
        return new History(caseId);
    }

    public Add add(@NonNull Integer sectionId, @NonNull Case testCase, java.util.List<CaseField> caseFields) {
        checkArgument(sectionId > 0, "projectId should be positive");
        return new Add(sectionId, testCase, caseFields);
    }

    public Delete delete(@NonNull Integer caseId) {
        checkArgument(caseId >= 0, "Case ID must be greater than zero");
        return new Delete(caseId);
    }

    public Get get(@NonNull Integer caseId) {
        checkArgument(caseId >= 0, "Case ID must be greater than zero");
        return new Get(caseId);
    }

    public List list(@NonNull Integer projectId, java.util.List<CaseField> caseFields) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new List(projectId, caseFields);
    }

    public List list(@NonNull Integer projectId, @NonNull Integer suiteId, java.util.List<CaseField> caseFields) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        checkArgument(suiteId > 0, "Suite ID must be greater than zero");
        return new List(projectId, suiteId, caseFields);
    }

    public Update update(@NonNull Case testCase, @NonNull Integer caseId, java.util.List<CaseField> caseFields) {
        checkArgument(caseId > 0, "Case ID must be greater than zero");
        return new Update(testCase, caseId, caseFields);
    }

    public static class Get extends Requester<Case> {
        private static final String REST_PATH = "get_case/%s";

        private Get(@NonNull Integer id) {
            super("GET", String.format(REST_PATH, id), Case.class);
        }
    }

    public static class List extends Requester<CasesList> {
        private static final String REST_PATH = "get_cases/%s";
        private final java.util.List<CaseField> caseFields;

        public List(@NonNull Integer project, java.util.List<CaseField> caseFields) {
            super("GET", String.format(REST_PATH, project), CasesList.class);
            this.caseFields = caseFields;
        }

        public List(@NonNull Integer project, @NonNull Integer suite, java.util.List<CaseField> caseFields) {
            super("GET", String.format(REST_PATH.concat("&suite_id=%s"), project, suite), CasesList.class);
            this.caseFields = caseFields;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return caseFields;
        }
    }

    public static class History extends Requester<CaseHistory> {
        private static final String REST_PATH = "get_history_for_case/%s";

        private History(@NonNull Integer id) {
            super("GET", String.format(REST_PATH, id), CaseHistory.class);
        }
    }

    public static class Add extends Requester<Case> {
        private static final String REST_PATH = "add_case/%s";
        private final java.util.List<CaseField> caseFields;
        private final Case testCase;

        public Add(@NonNull Integer sectionId, @NonNull Case testCase, java.util.List<CaseField> caseFields) {
            super("POST", String.format(REST_PATH, sectionId), Case.class);
            this.testCase = testCase;
            this.caseFields = caseFields;
        }

        @Override
        public Object getContent() {
            return testCase;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return caseFields;
        }
    }

    public static class Update extends Requester<Case> {
        private static final String REST_PATH = "update_case/%s";
        private final java.util.List<CaseField> caseFields;
        private final Case testCase;

        public Update(@NonNull Case testCase, @NonNull Integer id, java.util.List<CaseField> caseFields) {
            super("POST", String.format(REST_PATH, id), Case.class);
            this.testCase = testCase;
            this.caseFields = caseFields;
        }

        @Override
        public Object getContent() {
            return testCase;
        }

        @Override
        public Object getSupplementForDeserialization() {
            return caseFields;
        }
    }

    public static class Delete extends Requester<Void> {
        private static final String REST_PATH = "delete_case/%s";

        public Delete(@NonNull Integer id) {
            super("POST", String.format(REST_PATH, id), Void.class);
        }
    }
}
