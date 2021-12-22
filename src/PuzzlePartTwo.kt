object PuzzlePartTwo {
    private const val STEP_COUNT: Int = 50
    fun solve(inputLines: List<String>): Int {
        val imageEnhancement = ImageEnhancement.create(inputLines, STEP_COUNT)
        imageEnhancement.makeSteps(STEP_COUNT)
        return imageEnhancement.getLightPixelCount()
    }
}
