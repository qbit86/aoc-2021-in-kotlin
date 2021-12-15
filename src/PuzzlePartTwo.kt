object PuzzlePartTwo {
    private const val START = "start"
    private const val END = "end"

    fun solve(inputLines: List<String>): Int {
        val caveMap = CaveMap.create(inputLines)

        fun getPathCount(cave: String, explored: MutableSet<String>, reused: Boolean): Int {
            val wasAdded = CaveMap.isSmall(cave) && explored.add(cave)
            try {
                var result = 0
                val neighbors = caveMap.getNeighborsByCave(cave)
                for (neighbor in neighbors) {
                    if (neighbor == START) continue

                    if (neighbor == END) {
                        ++result
                        continue
                    }

                    if (CaveMap.isBig(neighbor)) {
                        result += getPathCount(neighbor, explored, reused)
                        continue
                    }

                    if (!explored.contains(neighbor)) {
                        result += getPathCount(neighbor, explored, reused)
                        continue
                    }

                    if (!reused)
                        result += getPathCount(neighbor, explored, true)
                }

                return result
            } finally {
                if (wasAdded)
                    explored.remove(cave)
            }
        }

        return getPathCount(START, mutableSetOf(), false)
    }
}
