import java.util.*

class DepthFirstSearchIterator<T>(g: Graph<T>, startingVertex: T) :
    MutableIterator<T> {
    private val visited: MutableSet<T> = HashSet()
    private val stack =
        Stack<Iterator<T>>()
    private var graph: Graph<T>? = null
    private var next: T? = null

    private var queueLevel: Int = 0

    override fun remove() {
        throw UnsupportedOperationException()
    }

    override fun hasNext(): Boolean {
        return next != null
    }

    override fun next(): T {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        return try {
            visited.add(this.next!!)
            this!!.next!!
        } finally {
            advance()
        }
    }

    private fun advance() {
        var neighbors = stack.peek()
        var hasNext = true
        do {
            while (!neighbors.hasNext()) { // No more nodes -> back out a level
                hasNext = false
                stack.pop()
                if (stack.isEmpty()) { // All done!
                    next = null
                    return
                }
                neighbors = stack.peek()
            }
            if (hasNext) queueLevel ++
            this.next = neighbors.next()
        } while (visited.contains(next))
        this.stack.push(this.graph!!.getNeighbors(this.next!!).iterator())
    }

    fun getQueueLevel() = queueLevel

    init {
        if (g.isVertexExist(startingVertex)) {
            stack.push(g.getNeighbors(startingVertex).iterator())
            graph = g
            next = startingVertex
        } else {
            throw IllegalArgumentException("Vertext does not exits")
        }
    }
}