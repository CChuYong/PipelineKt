interface MutablePipeCollection<T> : PipeCollection<T> {
    fun add(pipe: PipelineElement<T>)
    fun remove(pipe: PipelineElement<T>)
}
