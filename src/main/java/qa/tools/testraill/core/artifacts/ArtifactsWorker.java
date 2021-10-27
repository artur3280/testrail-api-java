package qa.tools.testraill.core.artifacts;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

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
        try (OutputStream out = Files.newOutputStream(Paths.get(path).toAbsolutePath(), CREATE, WRITE, TRUNCATE_EXISTING)) {
            JSON.writer(new DefaultPrettyPrinter())
                    .writeValue(out, this.objectClass);
        }
    }

    @SneakyThrows
    public void saveLocal(@NonNull String path) {
        if (path.isEmpty()) {
            throw new NullPointerException("path");
        }

        path += KEY_PREFIX.concat("backup.json");
        FileUtils.touch(new File(path));
        try (OutputStream out = Files.newOutputStream(Paths.get(path).toAbsolutePath(), CREATE, WRITE, TRUNCATE_EXISTING)) {
            JSON.writer(new DefaultPrettyPrinter())
                    .writeValue(out, this.objectClass);
        }
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
        try (OutputStream out = Files.newOutputStream(Paths.get(path).toAbsolutePath(), CREATE, WRITE, TRUNCATE_EXISTING)) {
            JSON.writer(new DefaultPrettyPrinter())
                    .writeValue(out, this.objectClass);
        }
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
