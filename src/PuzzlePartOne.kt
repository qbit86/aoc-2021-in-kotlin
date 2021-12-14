object PuzzlePartOne {
    fun solve(inputLines: List<String>): Int {
        val caveMap = CaveMap.create(inputLines)

        fun getPathCount(cave: String, explored: MutableSet<String>): Int {
            if (CaveMap.isSmall(cave))
                explored.add(cave)
            try {
                var result = 0
                val neighbors = caveMap.getNeighborsByCave(cave)
                for (neighbor in neighbors) {
                    if (neighbor == "end") {
                        ++result
                        continue
                    }

                    if (CaveMap.isBig(neighbor)) {
                        result += getPathCount(neighbor, explored)
                        continue
                    }

                    if (!explored.contains(neighbor))
                        result += getPathCount(neighbor, explored)
                }

                return result
            } finally {
                if (CaveMap.isSmall(cave))
                    explored.remove(cave)
            }
        }

        return getPathCount("start", mutableSetOf())
    }
}
