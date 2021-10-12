package qa.tools.testraill.requests;

import com.google.gson.reflect.TypeToken;
import lombok.NonNull;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.sections.Section;
import qa.tools.testraill.models.sections.SectionsList;

import static com.google.common.base.Preconditions.checkArgument;

public class Sections {

    public Add add(@NonNull Integer projectId, @NonNull Section section) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new Add(projectId, section);
    }

    public Get get(@NonNull Integer sectionId) {
        checkArgument(sectionId > 0, "Section ID must be greater than zero");
        return new Get(sectionId);
    }

    public List list(@NonNull Integer projectId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        return new List(projectId);
    }

    public List list(@NonNull Integer projectId, @NonNull Integer suiteId) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        checkArgument(suiteId > 0, "Suite ID must be greater than zero");
        return new List(projectId, suiteId);
    }

    public List list(@NonNull Integer projectId, @NonNull Integer suiteId, Integer limit, Integer offset) {
        checkArgument(projectId > 0, "Project ID must be greater than zero");
        checkArgument(suiteId > 0, "Suite ID must be greater than zero");
        checkArgument(limit >= 0, "Limit ID must be greater equals than zero");
        checkArgument(offset >= 0, "Offset ID must be greater or equals than zero");
        return new List(projectId, suiteId, limit, offset);
    }

    public Delete delete(@NonNull Integer sectionId) {
        checkArgument(sectionId > 0, "Section ID must be greater than zero");
        return new Delete(sectionId);
    }

    public Update update(@NonNull Integer sectionId, @NonNull Section section) {
        checkArgument(sectionId > 0, "Section ID must be greater than zero");
        return new Update(sectionId, section);
    }

    public static class Get extends Requester<Section> {
        private static final String REST_PATH = "get_section/%s";

        private Get(@NonNull Integer id) {
            super("GET", String.format(REST_PATH, id), Section.class);
        }
    }

    public static class List extends Requester<SectionsList> {
        private static final String REST_PATH = "get_sections/%s";

        public List(@NonNull Integer projectId, @NonNull Integer suiteId) {
            super("GET", String.format(REST_PATH.concat("&suite_id=%s"), projectId, suiteId), SectionsList.class);
        }

        public List(@NonNull Integer projectId) {
            super("GET", String.format(REST_PATH, projectId), SectionsList.class);
        }

        public List(@NonNull Integer projectId, @NonNull Integer suiteId, Integer limit, Integer offset) {
            super("GET", String.format(REST_PATH.concat("&suite_id=%s&limit=%s&offset=%s"), projectId, suiteId, limit, offset), SectionsList.class);
        }
    }

    public static class Add extends Requester<Section> {
        private static final String REST_PATH = "add_section/%s";
        private final Section section;

        public Add(@NonNull Integer projectId, @NonNull Section section) {
            super("POST", String.format(REST_PATH, projectId), Section.class);
            this.section = section;
        }

        @Override
        public Object getContent() {
            return section;
        }

    }

    public static class Update extends Requester<Section> {
        private static final String REST_PATH = "update_section/%s";
        private final Section section;

        public Update(@NonNull Integer sectionId, @NonNull Section suite) {
            super("POST", String.format(REST_PATH, sectionId), Section.class);
            this.section = suite;
        }

        @Override
        public Object getContent() {
            return section;
        }
    }

    public static class Delete extends Requester<Void> {
        private static final String REST_PATH = "delete_section/%s";

        public Delete(@NonNull Integer sectionId) {
            super("POST", String.format(REST_PATH, sectionId), Void.class);
        }
    }
}
