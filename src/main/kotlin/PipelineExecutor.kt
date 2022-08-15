interface PipelineExecutor<T> {
    fun execute(pipeline: PipelineElement<T>, props : T): T
}
