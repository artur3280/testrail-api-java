package qa.tools.testraill.core.artifacts;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NonNull;
import lombok.SneakyThrows;

public class Backup {
    private Object objectClass;

    public Backup(@NonNull Object object) {
        this.objectClass = object;
    }

    public Backup() {
    }

    @SneakyThrows
    public void saveToLocal() {
        Thread thread = new Thread(() -> {
            ArtifactsWorker<Object> artifact = new ArtifactsWorker<>(objectClass);
            artifact.saveLocal();

        });
        thread.start();
        thread.join();
    }

    @SneakyThrows
    public void saveToLocal(String path) {
        Thread thread = new Thread(() -> {
            ArtifactsWorker<Object> artifact = new ArtifactsWorker<Object>(this.objectClass);
            artifact.saveLocal(path);
        });
        thread.start();
        thread.join();
    }

    @SneakyThrows
    public void saveToLocal(String path, String fileName) {
        Thread thread = new Thread(() -> {
            ArtifactsWorker<Object> artifact = new ArtifactsWorker<Object>(this.objectClass);
            artifact.saveLocal(path, fileName);
        });
        thread.start();
        thread.join();
    }

    public <T> T asObject(@NonNull String path, @NonNull Class<T> cls) {
        ArtifactsWorker<T> artifactsWorker = new ArtifactsWorker<>();
        return artifactsWorker.asObject(path, cls);
    }

    public <T> T asObject(@NonNull String path, @NonNull TypeReference<T> typeRef) {
        ArtifactsWorker<T> artifactsWorker = new ArtifactsWorker<>();
        return artifactsWorker.asObject(path, typeRef);
    }

    public <T> T asObject(@NonNull Class<T> cls) {
        ArtifactsWorker<T> artifactsWorker = new ArtifactsWorker<>();
        return artifactsWorker.asObject(cls);
    }

    public <T> T asObject(@NonNull TypeReference<T> typeRef) {
        ArtifactsWorker<T> artifactsWorker = new ArtifactsWorker<>();
        return artifactsWorker.asObject(typeRef);
    }
}
