package qa.tools.testraill.requests;

import lombok.NonNull;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.runs.Run;
import qa.tools.testraill.models.runs.RunsList;

import static com.google.common.base.Preconditions.checkArgument;

public class Runs {
    public Add add(@NonNull Integer projectId, @NonNull Run run) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new Add(projectId, run);
    }

    public Get get(@NonNull Integer runId) {
        checkArgument(runId > 0, "Run ID must be greater than zero");
        return new Get(runId);
    }

    public List list(@NonNull Integer projectId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new List(projectId);
    }

    public Delete delete(@NonNull Integer runId) {
        checkArgument(runId > 0, "Run ID must be greater than zero");
        return new Delete(runId);
    }

    public Update update(@NonNull Integer runId, @NonNull Run run) {
        checkArgument(runId > 0, "Run ID must be greater than zero");
        return new Update(runId, run);
    }

    public Close close(@NonNull Integer runId) {
        checkArgument(runId > 0, "Run ID must be greater than zero");
        return new Close(runId);
    }

    public static class Get extends Requester<Run> {
        private static final String REST_PATH = "get_run/%s";

        private Get(@NonNull Integer runId) {
            super("GET", String.format(REST_PATH, runId), Run.class);
        }
    }

    public static class List extends Requester<RunsList> {
        private static final String REST_PATH = "get_runs/%s";

        public List(@NonNull Integer projectId) {
            super("GET", String.format(REST_PATH, projectId), RunsList.class);
        }
    }

    public static class Add extends Requester<Run> {
        private static final String REST_PATH = "add_run/%s";
        private final Run run;

        public Add(@NonNull Integer projectId, @NonNull Run run) {
            super("POST", String.format(REST_PATH, projectId), Run.class);
            this.run = run;
        }

        @Override
        public Object getContent() {
            return run;
        }
    }

    public static class Update extends Requester<Run> {
        private static final String REST_PATH = "update_run/%s";
        private final Run run;

        public Update(@NonNull Integer runId, @NonNull Run run) {
            super("POST", String.format(REST_PATH, runId), Run.class);
            this.run = run;
        }

        @Override
        public Object getContent() {
            return run;
        }
    }

    public static class Delete extends Requester<Void> {
        private static final String REST_PATH = "delete_run/%s";

        public Delete(@NonNull Integer runId) {
            super("POST", String.format(REST_PATH, runId), Void.class);
        }
    }

    public static class Close extends Requester<Run> {
        private static final String REST_PATH = "close_run/%s";

        public Close(@NonNull Integer runId) {
            super("POST", String.format(REST_PATH, runId), Run.class);
        }
    }
}
