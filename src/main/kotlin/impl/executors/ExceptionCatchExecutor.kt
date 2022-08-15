package impl.executors

import PipelineElement
import PipelineExecutor

/**
 * A Simple pipeline executor which ignores all exceptions and continue pipeline execution if exception occurs.
 * @param log prints error message to console if exception occurs.
 */
class ExceptionCatchExecutor<T>(private val log: Boolean) : PipelineExecutor<T> {
    override fun execute(pipeline: PipelineElement<T>, props: T): T {
        try{
            return pipeline.execute(props)
        }catch(e : Exception){
            if(log) System.err.println("Exception in pipeline: ${pipeline.javaClass.name}")
            return props
        }
    }
}
