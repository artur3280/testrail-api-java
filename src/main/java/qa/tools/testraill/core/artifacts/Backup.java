package qa.tools.testraill.core.artifacts;

import com.fasterxml.jackson.core.type.TypeReference;
import lombok.NonNull;

public class Backup {
    private final Object objectClass;

    public Backup(@NonNull Object object) {
        this.objectClass = object;
    }

    public void saveToLocal() {
        ArtifactsWorker<Object> artifact = new ArtifactsWorker<Object>(this.objectClass);
        artifact.saveLocal();
    }

    public void saveToLocal(String path) {
        ArtifactsWorker<Object> artifact = new ArtifactsWorker<Object>(this.objectClass);
        artifact.saveLocal(path);
    }

    public void saveToLocal(String path, String fileName) {
        ArtifactsWorker<Object> artifact = new ArtifactsWorker<Object>(this.objectClass);
        artifact.saveLocal(path, fileName);
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
