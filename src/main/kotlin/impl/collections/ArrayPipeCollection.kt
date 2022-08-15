package impl.collections

import PipeCollection
import PipelineElement

open class ArrayPipeCollection<T>(internal var pipeArray : Array<PipelineElement<T>> = arrayOf()) : PipeCollection<T>{
    internal var iterationIndex = 0
    override fun hasNext(): Boolean {
        return iterationIndex < this.pipeArray.size
    }

    override fun next(): PipelineElement<T> {
        return this.pipeArray[iterationIndex++]
    }
}
