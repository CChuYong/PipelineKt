interface PipeCollection<T> {
    fun hasNext(): Boolean
    fun next() : PipelineElement<T>
}
