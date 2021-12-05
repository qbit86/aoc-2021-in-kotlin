fun main() {
    val testInputLines = readInput("test-input")
    val inputLines = readInput("input")

    check(PuzzlePartOne.solve(testInputLines) == 4512)
    println(PuzzlePartOne.solve(inputLines))
}
