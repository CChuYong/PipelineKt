package impl.collections

import MutablePipeCollection
import PipelineElement

open class MutableArrayPipeCollection<T>() : ArrayPipeCollection<T>(), MutablePipeCollection<T> {
    override fun add(pipe: PipelineElement<T>) {
        val newArray = Array<PipelineElement<T>>(this.pipeArray.size + 1) {id->
            return@Array if(id + 1 < this.pipeArray.size) this.pipeArray[id] else pipe
        }
        this.pipeArray = newArray
    }

    override fun remove(pipe: PipelineElement<T>) {
        TODO("Not yet implemented")
    }
}
