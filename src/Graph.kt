import java.util.*

class Graph<T> {
    private val map: MutableMap<T, MutableSet<T>?>
    fun addEdge(src: T?, destination: T?): Graph<*> {
        if (src != null) {
            require(!(src === destination || src == destination)) { "Source and Destination can not be same" }
            var desitinations = map[src]
            if (desitinations == null) {
                desitinations = HashSet()
            }
            if (destination != null) {
                desitinations.add(destination)
                val destinationsOfDestination: Set<T>? = map[destination]
                if (destinationsOfDestination == null) {
                    map[destination] = HashSet()
                }
            }
            map[src] = desitinations
        } else {
            throw IllegalArgumentException("Invalid Source node")
        }
        return this
    }

    fun getNeighbors(vertex: T): Iterable<T> {
        val neighbors: Set<T>? = map[vertex]
        return if (neighbors == null || neighbors.isEmpty()) {
            emptySet()
        } else {
            Collections.unmodifiableSet(neighbors)
        }
    }

    fun isVertexExist(vertex: T): Boolean {
        return map.containsKey(vertex)
    }

    override fun toString(): String {
        val sb = StringBuilder("Graph{")
        sb.append("map=").append(map)
        sb.append('}')
        return sb.toString()
    }

    init {
        map = HashMap()
    }
}