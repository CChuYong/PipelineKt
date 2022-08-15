interface PipelineElement<T> {
    fun execute(props : T) : T
}
