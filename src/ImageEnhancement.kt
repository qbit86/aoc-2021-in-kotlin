data class Point(val x: Int, val y: Int)

internal class ImageEnhancement private constructor(
    private val rules: String,
    private var lightPixels: MutableSet<Point>,
    initialLowerBound: Point,
    initialUpperBound: Point,
    maxStepCount: Int
) {
    private val lowerBound = Point(initialLowerBound.x - maxStepCount - 2, initialLowerBound.y - maxStepCount - 2)
    private val upperBound = Point(initialUpperBound.x + maxStepCount + 2, initialUpperBound.y + maxStepCount + 2)

    internal fun getLightPixelCount() = lightPixels.size

    internal fun makeSteps(stepCount: Int) {
        for (i in 0 until stepCount)
            makeStep()
    }

    private fun makeStep() {
        val oldLightPixels: Set<Point> = lightPixels
        val newLightPixels = mutableSetOf<Point>()
        for (y in lowerBound.y..upperBound.y)
            for (x in lowerBound.x..upperBound.x) {
                val current = Point(x, y)
                val encodedNeighbors = encodeNeighbors(oldLightPixels, current)
                if (shouldBeLight(encodedNeighbors))
                    newLightPixels.add(current)
            }

        lightPixels.clear()
        lightPixels = newLightPixels
    }

    private fun shouldBeLight(encodedNeighbors: Int): Boolean =
        0 <= encodedNeighbors && encodedNeighbors < rules.length && rules[encodedNeighbors] != '.'

    private fun encodeNeighbors(lightPixels: Set<Point>, point: Point): Int {
        var result = 0
        for (dy in -1..1)
            for (dx in -1..1) {
                val current = Point(point.x + dx, point.y + dy)
                result = result shl 1
                if (isLightPixel(lightPixels, current))
                    result = result or 1
            }
        return result
    }

    private fun isLightPixel(lightPixels: Set<Point>, point: Point): Boolean {
        val clampedX = point.x.coerceIn(lowerBound.x, upperBound.x)
        val clampedY = point.y.coerceIn(lowerBound.y, upperBound.y)
        return lightPixels.contains(Point(clampedX, clampedY))
    }

    companion object {
        internal fun create(inputLines: List<String>, maxStepCount: Int): ImageEnhancement {
            if (maxStepCount <= 0)
                throw IllegalArgumentException("Argument 'maxStepCount' must be greater than zero.")

            val lightPixels = mutableSetOf<Point>()
            var lowerBound = Point(Int.MAX_VALUE, Int.MAX_VALUE)
            var upperBound = Point(Int.MIN_VALUE, Int.MIN_VALUE)
            for (rowIndex in 2 until inputLines.size) {
                val line = inputLines[rowIndex]
                for (columnIndex in line.indices) {
                    if (line[columnIndex] == '.')
                        continue
                    val current = Point(columnIndex, rowIndex)
                    lightPixels.add(current)
                    lowerBound = min(current, lowerBound)
                    upperBound = max(current, upperBound)
                }
            }

            return ImageEnhancement(inputLines[0], lightPixels, lowerBound, upperBound, maxStepCount)
        }

        private fun min(left: Point, right: Point) =
            Point(kotlin.math.min(left.x, right.x), kotlin.math.min(left.y, right.y))

        private fun max(left: Point, right: Point) =
            Point(kotlin.math.max(left.x, right.x), kotlin.math.max(left.y, right.y))
    }
}
