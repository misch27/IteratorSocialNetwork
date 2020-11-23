import java.util.*

class BreadthFirstIterator<T>(g: Graph<T>, startingVertex: T) : MutableIterator<T> {
    private val visited: MutableSet<T> = HashSet()
    private val queue: Queue<T> = LinkedList()
    private var graph: Graph<T>? = null

    private var queueLevel: Int = 0
    override fun remove() {
        throw UnsupportedOperationException()
    }

    override fun hasNext(): Boolean {
        return !queue.isEmpty()
    }

    override fun next(): T {
        if (!hasNext()) throw NoSuchElementException()
        //removes from front of queue
        val next = queue.remove()
        if (queue.size == 0) queueLevel++
        for (neighbor in graph!!.getNeighbors(next)) {
            if (!visited.contains(neighbor)) {
                queue.add(neighbor)
                visited.add(neighbor)
            }
        }
        return next
    }

    fun getQueueLevel() = queueLevel

        init {
        if (g.isVertexExist(startingVertex)) {
            graph = g
            queue.add(startingVertex)
            visited.add(startingVertex)
        } else {
            throw IllegalArgumentException("Vertext does not exits")
        }
    }
}