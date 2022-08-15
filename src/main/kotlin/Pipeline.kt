import java.util.concurrent.CompletableFuture

interface Pipeline<T> {
    fun runPipeline(defaultValue : T) : CompletableFuture<Boolean>
}
