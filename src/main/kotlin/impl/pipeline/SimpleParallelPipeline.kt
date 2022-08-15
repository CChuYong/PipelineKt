package impl.pipeline

import HandlerType
import PipeCollection
import Pipeline
import PipelineExecutor
import PipelineHandler
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * A simple [Pipeline] implementation which executes every pipeline in synchronously, but execute pipelines in parallel context
 */
class SimpleParallelPipeline<T>(
    private val pipeCollection : PipeCollection<T>,
    private val handlers : Collection<PipelineHandler>,
    private val executor : PipelineExecutor<T>
) : Pipeline<T> {
    private val handlerMap = mutableMapOf<HandlerType, MutableCollection<PipelineHandler>>()
    private var running : Boolean = false
    private val parallelExecutor : ExecutorService = Executors.newSingleThreadExecutor()

    /**
     * Used to Run the pipeline with default values.
     * @param defaultValue the default value which pipeline used to bootstrap.
     */
    @Synchronized
    override fun runPipeline(defaultValue : T) : CompletableFuture<Boolean> {
        if(running) throw RuntimeException("Pipeline is already running")
        running = true
        var props = defaultValue
        handlerMap[HandlerType.ONCE_ON_START]?.forEach(PipelineHandler::handle)
        while(pipeCollection.hasNext()){
            val element = pipeCollection.next()
            handlerMap[HandlerType.PER_ITERATION]?.forEach(PipelineHandler::handle)
            val future = parallelExecutor.submit(Callable<T> { executor.execute(element, props) })
            props = future.get()
        }
        handlerMap[HandlerType.ONCE_ON_END]?.forEach(PipelineHandler::handle)
        running = false
        return CompletableFuture.completedFuture(true)
    }

    /**
     * Used to bake handler collection for each pipeline to optimize execution time
     */
    private fun bakeHandlers() {
        if(running) throw RuntimeException("Handler baking is not allowed while pipeline is running")
        handlerMap.clear()
        handlers.forEach { handler ->
            val handlerType = handler.getHandlerType()
            val handlerCollection = handlerMap.getOrDefault(handlerType, mutableListOf())
            handlerCollection.add(handler)
            handlerMap[handlerType] = handlerCollection
        }
    }

}
