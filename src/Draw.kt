class Draw private constructor(private val numberByTurn: List<Int>, private val turnByNumber: List<Int>) {
    fun isMarked(number: Int, turn: Int): Boolean = turnByNumber[number] <= turn

    fun getNumberByTurn(turn: Int) = numberByTurn[turn]

    fun getTurnByNumber(number: Int) = turnByNumber[number]

    companion object {
        internal fun create(drawInput: String): Draw {
            val numberByTurn: List<Int> = drawInput.split(',').map { it.toInt() }
            val turnByNumber: MutableList<Int> = MutableList(100) { Int.MAX_VALUE }
            for (turn in 0 until numberByTurn.count()) {
                val number = numberByTurn[turn]
                turnByNumber[number] = turn
            }

            return Draw(numberByTurn, turnByNumber)
        }
    }
}
