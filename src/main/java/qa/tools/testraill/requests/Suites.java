package qa.tools.testraill.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NonNull;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.suites.Suite;

import static com.google.common.base.Preconditions.checkArgument;

public class Suites {

    public Add add(@NonNull Integer projectId, @NonNull Suite suite) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new Add(projectId, suite);
    }

    public Get get(@NonNull Integer suiteId) {
        checkArgument(suiteId > 0, "Suite ID must be greater than zero");
        return new Get(suiteId);
    }

    public List list(@NonNull Integer projectId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new List(projectId);
    }

    public Delete delete(@NonNull Integer suiteId) {
        checkArgument(suiteId > 0, "Suite ID must be greater than zero");
        return new Delete(suiteId);
    }

    public Update update(@NonNull Integer suiteId, @NonNull Suite suite) {
        checkArgument(suiteId > 0, "Suite ID must be greater than zero");
        return new Update(suiteId, suite);
    }

    public static class Get extends Requester<Suite> {
        private static final String REST_PATH = "get_suite/%s";

        private Get(@NonNull Integer id) {
            super("GET", String.format(REST_PATH, id), Suite.class);
        }
    }

    public static class List extends Requester<java.util.List<Suite>> {
        private static final String REST_PATH = "get_suites/%s";

        public List(@NonNull Integer projectId) {
            super("GET", String.format(REST_PATH, projectId), new TypeReference<java.util.List<Suite>>() {
            });
        }
    }

    public static class Add extends Requester<Suite> {
        private static final String REST_PATH = "add_suite/%s";
        private final Suite testSuite;

        public Add(@NonNull Integer projectId, @NonNull Suite testSuite) {
            super("POST", String.format(REST_PATH, projectId), Suite.class);
            this.testSuite = testSuite;
        }

        @Override
        public Object getContent() {
            return testSuite;
        }

    }

    public static class Update extends Requester<Suite> {
        private static final String REST_PATH = "update_suite/%s";
        private final Suite testSuite;

        public Update(@NonNull Integer suiteId, @NonNull Suite suite) {
            super("POST", String.format(REST_PATH, suiteId), Suite.class);
            this.testSuite = suite;
        }

        @Override
        public Object getContent() {
            return testSuite;
        }
    }

    public static class Delete extends Requester<Void> {
        private static final String REST_PATH = "delete_suite/%s";

        public Delete(@NonNull Integer suiteId) {
            super("POST", String.format(REST_PATH, suiteId), Void.class);
        }
    }
}
