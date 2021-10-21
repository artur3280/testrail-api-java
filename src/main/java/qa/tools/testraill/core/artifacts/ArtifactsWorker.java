package qa.tools.testraill.core.artifacts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import qa.tools.testraill.core.CustomLogger;

import java.io.File;

public class ArtifactsWorker<T> {
    private Object objectClass;
    private static final String KEY_PREFIX = "/data_";
    private static final ObjectMapper JSON = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    public ArtifactsWorker(final Object objectClass) {
        this.objectClass = objectClass;
    }

    public ArtifactsWorker() {
    }

    @SneakyThrows
    public void saveLocal() {
        String path = "./artifact/".concat(KEY_PREFIX).concat("backup.json");
        FileUtils.touch(new File(path));
        JSON.writer(new DefaultPrettyPrinter())
                .writeValue(new File(path), this.objectClass);
        waitToAppear(new File(path));
    }

    @SneakyThrows
    public void saveLocal(@NonNull String path) {
        if (path.isEmpty()) {
            throw new NullPointerException("path");
        }

        path += KEY_PREFIX.concat("backup.json");
        FileUtils.touch(new File(path));
        JSON.writer(new DefaultPrettyPrinter())
                .writeValue(new File(path), this.objectClass);
        waitToAppear(new File(path));
    }

    @SneakyThrows
    public void saveLocal(@NonNull String path, @NonNull String jsonName) {
        if (path.isEmpty()) {
            throw new NullPointerException("path");
        }

        if (jsonName.isEmpty()) {
            throw new NullPointerException("jsonName");
        }

        path += KEY_PREFIX.concat(jsonName).concat(".json");
        FileUtils.touch(new File(path));
        JSON.writer(new DefaultPrettyPrinter())
                .writeValue(new File(path), this.objectClass);
        waitToAppear(new File(path));
    }

    @SneakyThrows
    private static void waitToAppear(@NonNull File file) {
        do {
            CustomLogger.log.info("check file");
            boolean fileExist = file.exists()
                    && file.canWrite()
                    && file.canRead();
            if(fileExist){
                break;
            }else {
                Thread.sleep(1000);
            }

        } while (true);
    }

    @SneakyThrows
    public T asObject(@NonNull String path, @NonNull Class<T> cls) {
        if (path.isEmpty()) {
            throw new NullPointerException("Class is null");
        }
        return JSON.readValue(new File(path), cls);
    }

    @SneakyThrows
    public T asObject(@NonNull String path, @NonNull TypeReference<T> typeRef) {
        if (path.isEmpty()) {
            throw new NullPointerException("typeRef is null");
        }
        return JSON.readValue(new File(path), typeRef);
    }

    @SneakyThrows
    public T asObject(@NonNull Class<T> cls) {
        String path = "./artifact/".concat(KEY_PREFIX).concat("backup.json");
        if (path.isEmpty()) {
            throw new NullPointerException("Class is null");
        }
        return JSON.readValue(new File(path), cls);
    }

    @SneakyThrows
    public T asObject(@NonNull TypeReference<T> typeRef) {
        String path = "./artifact/".concat(KEY_PREFIX).concat("backup.json");
        if (path.isEmpty()) {
            throw new NullPointerException("typeRef is null");
        }
        return JSON.readValue(new File(path), typeRef);
    }
}
