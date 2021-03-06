package qa.tools.testraill.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.fields.CaseField;

public class CaseFields {
    public List list() {
        return new List();
    }

    public static class List extends Requester<java.util.List<CaseField>> {
        private static final String REST_PATH = "get_case_fields";

        private List() {
            super("GET", REST_PATH, new TypeReference<java.util.List<CaseField>>(){});
        }
    }
}
