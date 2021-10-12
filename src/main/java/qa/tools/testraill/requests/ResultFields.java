package qa.tools.testraill.requests;

import com.google.gson.reflect.TypeToken;
import qa.tools.testraill.core.Requester;
import qa.tools.testraill.models.fields.CaseField;
import qa.tools.testraill.models.fields.ResultField;

public class ResultFields {
    public List list() {
        return new List();
    }

    public static class List extends Requester<java.util.List<ResultField>> {
        private static final String REST_PATH = "get_result_fields";

        private List() {
            super("GET", REST_PATH, new TypeToken<java.util.List<ResultField>>(){});
        }
    }
}
