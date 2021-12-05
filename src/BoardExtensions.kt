import kotlin.math.min

private fun Board.getColumns(): List<List<Int>> =
    (0 until this.width).map { columnIndex: Int -> this.rows.map { it[columnIndex] } }

fun Board.computeWinningTurn(draw: Draw): Int {
    val winningTurnForRows: Int = computeWinningTurn(draw, this.rows)
    val winningTurnForColumns: Int = computeWinningTurn(draw, this.getColumns())
    return min(winningTurnForRows, winningTurnForColumns)
}

private fun computeWinningTurn(draw: Draw, lines: List<List<Int>>): Int = lines.minOf { computeFinishingTurn(draw, it) }

private fun computeFinishingTurn(draw: Draw, line: List<Int>): Int = line.maxOf { draw.getTurnByNumber(it) }

fun Board.computeScore(draw: Draw, turn: Int): Int {
    val number: Int = draw.getNumberByTurn(turn)
    val unmarkedNumbers: List<Int> = this.rows.flatten().filter { !draw.isMarked(it, turn) }
    val sum: Int = unmarkedNumbers.sum()
    return sum * number
}
