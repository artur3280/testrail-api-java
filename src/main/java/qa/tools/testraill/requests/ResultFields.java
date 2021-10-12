package qa.tools.testraill.requests;

import com.fasterxml.jackson.core.type.TypeReference;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.fields.ResultField;

public class ResultFields {
    public List list() {
        return new List();
    }

    public static class List extends Requester<java.util.List<ResultField>> {
        private static final String REST_PATH = "get_result_fields";

        private List() {
            super("GET", REST_PATH, new TypeReference<java.util.List<ResultField>>(){});
        }
    }
}
