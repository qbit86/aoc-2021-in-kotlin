object PuzzlePartTwo {
    fun solve(inputLines: List<String>): Int {
        val drawInput: String = inputLines[0]
        val draw = Draw.create(drawInput)

        val boardInputs: List<List<String>> = inputLines.drop(1).chunked(6).map { it.drop(1) }
        val boards: List<Board> = boardInputs.map { Board.create(it) }

        val boardTurnPairs: List<Pair<Board, Int>> = boards.map { it to it.computeWinningTurn(draw) }
        val (lastWinningBoard, turn) = boardTurnPairs.maxByOrNull { it.second }!!

        return lastWinningBoard.computeScore(draw, turn)
    }
}
