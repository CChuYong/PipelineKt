package impl.collections

import PipeCollection
import PipelineElement

open class ArrayPipeCollection<T>(internal var pipeArray : Array<PipelineElement<T>> = arrayOf()) : PipeCollection<T>{
    override fun hasNext(): Boolean {
        TODO("Not yet implemented")
    }

    override fun next(): PipelineElement<T> {
        TODO("Not yet implemented")
    }
}
