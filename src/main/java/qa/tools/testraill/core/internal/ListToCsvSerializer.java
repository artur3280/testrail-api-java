package qa.tools.testraill.core.internal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.common.base.Joiner;

import java.io.IOException;
import java.util.List;

public class ListToCsvSerializer extends JsonSerializer<List<?>> {

    @Override
    public void serialize(List<?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (value != null) {
            jgen.writeString(Joiner.on(',').join(value));
        }
    }
}
