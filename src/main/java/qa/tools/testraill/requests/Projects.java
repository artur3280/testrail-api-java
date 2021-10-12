package qa.tools.testraill.requests;

import com.google.gson.Gson;
import lombok.NonNull;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.projects.Project;
import qa.tools.testraill.models.projects.ProjectList;

import static com.google.common.base.Preconditions.checkArgument;

public class Projects{

    public Add add(@NonNull Project project) {
        return new Add(project);
    }

    public Get get(@NonNull Integer projectId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new Get(projectId);
    }

    public List list() {
        return new List();
    }

    public Delete delete(@NonNull Integer projectId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new Delete(projectId);
    }

    public Update update(@NonNull Project project, @NonNull Integer projectId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new Update(project, projectId);
    }

    public static class Get extends Requester<Project> {
        private static final String REST_PATH = "get_project/%s";

        private Get(@NonNull Integer projectId) {
            super("GET", String.format(REST_PATH, projectId), Project.class);
        }
    }

    public static class List extends Requester<ProjectList> {
        private static final String REST_PATH = "get_projects";

        public List() {
            super("GET", REST_PATH, ProjectList.class);
        }
    }

    public static class Add extends Requester<Project> {
        private static final String REST_PATH = "add_project";
        private final Project project;

        public Add(@NonNull Project project) {
            super("POST", REST_PATH, Project.class);
            System.out.println("body");
            System.out.println(new Gson().toJson(project));
            if (project == null) {
                throw new NullPointerException("project");
            }
            this.project = project;
        }

        @Override
        public Object getContent() {
            return project;
        }
    }

    public static class Update extends Requester<Project> {
        private static final String REST_PATH = "update_project/%s";
        private final Project project;

        public Update(@NonNull Project project, @NonNull Integer projectId) {
            super("POST", String.format(REST_PATH, projectId), Project.class);
            System.out.println("body");
            System.out.println(new Gson().toJson(project));

            if (project == null) {
                throw new NullPointerException("project");
            }
            this.project = project;
        }

        @Override
        public Object getContent() {
            return project;
        }
    }

    public static class Delete extends Requester<Void> {
        private static final String REST_PATH = "delete_project/%s";

        public Delete(@NonNull Integer projectId) {
            super("POST", String.format(REST_PATH, projectId), Void.class);
        }
    }
}
