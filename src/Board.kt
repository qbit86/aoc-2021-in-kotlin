class Board private constructor(val rows: List<List<Int>>, val width: Int) {
    companion object {
        internal fun create(boardInput: List<String>): Board {
            val rows: List<List<Int>> = boardInput.map { rowLine: String ->
                rowLine.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            }
            val width: Int = rows.minOf { it.count() }
            return Board(rows, width)
        }
    }
}
