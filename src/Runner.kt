fun main() {
    // https://adventofcode.com/2021/day/4

    val testInputLines = readInput("test-input")
    val inputLines = readInput("input")

    check(PuzzlePartOne.solve(testInputLines) == 4512)
    println(PuzzlePartOne.solve(inputLines))

    check(PuzzlePartTwo.solve(testInputLines) == 1924)
    println(PuzzlePartTwo.solve(inputLines))
}
