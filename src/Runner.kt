fun main() {
    val testInputLines = readInput("test-input")
    val inputLines = readInput("input")

    check(PuzzlePartOne.solve(testInputLines) == 35)
    println(PuzzlePartOne.solve(inputLines))

    check(PuzzlePartTwo.solve(testInputLines) == 3351)
    println(PuzzlePartTwo.solve(inputLines))
}
