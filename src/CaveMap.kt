class CaveMap private constructor(private val neighborsByCave: Map<String, Collection<String>>) {
    fun getNeighborsByCave(cave: String): Collection<String> = neighborsByCave[cave].orEmpty()

    companion object {
        internal fun create(inputLines: List<String>): CaveMap {
            val endpointsList: List<List<String>> = inputLines.map { it.split("-") }

            val neighborsByCave: MutableMap<String, MutableSet<String>> = mutableMapOf()
            fun addEdge(tail: String, head: String) {
                val neighbors = neighborsByCave[tail]
                if (neighbors != null)
                    neighbors.add(head)
                else
                    neighborsByCave[tail] = mutableSetOf(head)
            }

            for (endpoints in endpointsList) {
                addEdge(endpoints[0], endpoints[1])
                addEdge(endpoints[1], endpoints[0])
            }

            return CaveMap(neighborsByCave)
        }

        internal fun isBig(cave: String): Boolean = cave[0].isUpperCase()
        internal fun isSmall(cave: String): Boolean = !isBig(cave)
    }
}
