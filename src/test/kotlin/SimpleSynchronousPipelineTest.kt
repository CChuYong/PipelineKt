import impl.collections.MutableArrayPipeCollection
import impl.executors.ExceptionCatchExecutor
import impl.pipeline.SimpleSynchronousPipeline
import kotlin.test.Test
import kotlin.test.assertEquals

class SimpleSynchronousPipelineTest {
    @Test fun createNewPipeline(){
        val coll = MutableArrayPipeCollection<Unit>();
        val pipeline = SimpleSynchronousPipeline(coll, executor = ExceptionCatchExecutor());
    }

    @Test fun executePipeline(){
        val coll = MutableArrayPipeCollection<Unit>();
        val pipeline = SimpleSynchronousPipeline(coll, executor = ExceptionCatchExecutor());
        pipeline.runPipeline(Unit);
    }

    @Test fun executePipelineWithElements(){
        val coll = MutableArrayPipeCollection<Unit>();
        var pipelineThread = ""
        coll.add(object : PipelineElement<Unit> {
            override fun execute(props: Unit) {
                pipelineThread = Thread.currentThread().name
                println("Pipeline Working in ${Thread.currentThread().name}")
            }
        });
        val pipeline = SimpleSynchronousPipeline(coll, executor = ExceptionCatchExecutor());
        pipeline.runPipeline(Unit);

        //It guarantees that the pipeline is working in the same thread as the pipeline element
        assertEquals(pipelineThread, Thread.currentThread().name)
    }
}
