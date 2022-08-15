package impl.executors

import PipelineElement
import PipelineExecutor

/**
 * A Simple pipeline executor which executes the pipeline in a sequential manner.
 * If error occurs during pipeline, pipeline will killed by exception.
 */
class SimpleExecutor<T>() : PipelineExecutor<T> {
    override fun execute(pipeline: PipelineElement<T>, props: T): T {
        return pipeline.execute(props)
    }
}
