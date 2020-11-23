data class User(
    val id: Int,
    val firstName: String,
    val secondName: String,
    val age: Int
)

fun findFriendsByUser(user: User, graph: Graph<User>) {
    val bfs = BreadthFirstIterator(graph, user)
    println("Friends of user ${user.firstName} ${user.secondName}")
    while (bfs.hasNext() && bfs.getQueueLevel() <= 1) {
        val processedQueueLevel = bfs.getQueueLevel()
        val processedUser = bfs.next()
        if (processedQueueLevel == 1) {
            println(processedUser)
        }
    }
}

fun findThroughHandshakes(originalUser: User, soughtUser: User, graph: Graph<User>) {
    val dfs = DepthFirstSearchIterator(graph, originalUser)
    while (dfs.hasNext()) {
        val processedQueueLevel = dfs.getQueueLevel()
        val processedUser = dfs.next()
        if (processedUser == soughtUser) {
            println("Number of handshakes is $processedQueueLevel")
            break
        }

    }
}

fun main() {
    val graph = Graph<User>()
    val p1 = User(id = 1, firstName = "John", secondName = "Vladov", age = 23)
    val p2 = User(id = 2, firstName = "Oleg", secondName = "Olurtshov", age = 22)
    val p3 = User(id = 3, firstName = "Vladislav", secondName = "Ponyatovskiy", age = 18)
    val p4 = User(id = 4, firstName = "Ivan", secondName = "Grozniy", age = 52)
    val p5 = User(id = 5, firstName = "Elizabeth II", secondName = "Alexandra-Maria", age = 94)
    val p6 = User(
        id = 6,
        firstName = "Catherine II",
        secondName = "Sophia Augusta Friederike von Anhalt-Zerbst-Dornburg ",
        age = 55
    )

    graph.addEdge(p1, p3)
    graph.addEdge(p1, p2)
    graph.addEdge(p2, p4)
    graph.addEdge(p4, p5)
    graph.addEdge(p5, null)
    graph.addEdge(p1, p6)

    findFriendsByUser(user = p1, graph = graph)
    println()
    findThroughHandshakes(originalUser = p1, soughtUser = p5, graph = graph)
}