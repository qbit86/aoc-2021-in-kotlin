fun main() {
    val testInputLines = readInput("test-input")
    val inputLines = readInput("input")

    check(PuzzlePartOne.solve(testInputLines) == 0)
    println(PuzzlePartOne.solve(inputLines))
}
