fun main() {
    // Day 12: Passage Pathing https://adventofcode.com/2021/day/12

    check(PuzzlePartOne.solve(readInput("test-input")) == 10)
    check(PuzzlePartOne.solve(readInput("test-input-01")) == 19)
    check(PuzzlePartOne.solve(readInput("test-input-02")) == 226)

    println(PuzzlePartOne.solve(readInput("input")))
}
