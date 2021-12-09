package adventofcode2021.days

import adventofcode2021.dataAsMatrix

fun main() {
    println("Part One Example: ${getSmallest(day8ExData)}")
    val day8Data = dataAsMatrix(9)
    println(day8Data)
    // got 551 --> too high
    println("Part One Data: ${getSmallest(day8Data)}")
}

var day8ExData = listOf(
    listOf(2, 1, 9, 9, 9, 4, 3, 2, 1, 0),
    listOf(3, 9, 8, 7, 8, 9, 4, 9, 2, 1),
    listOf(9, 8, 5, 6, 7, 8, 9, 8, 9, 2),
    listOf(8, 7, 6, 7, 8, 9, 6, 7, 8, 9),
    listOf(9, 8, 9, 9, 9, 6, 5, 6, 7, 8)
)

// double for loop ?? -.-

fun getSmallest(matrix: List<List<Int>>): Int {
    val matrixHeight = matrix.size - 1
    var ans = 0
    for (i in matrix.indices) {
        val rowLength = matrix[i].size - 1
        for ( j in matrix[i].indices) {
            val rowOrientations = arrayListOf(-1, 0, 1, 0)
            val colOrientations = arrayListOf(0, -1, 0, 1)
            var inRange = true
            for (n in 0 until rowOrientations.size) {
                val row = i + rowOrientations[n]
                val col = j + colOrientations[n]
                if (row in 0..matrixHeight &&
                    col in 0..rowLength &&
                    matrix[row][col] <= matrix[i][j]
                ) {
                    // the element is out of range and won't exist
                    inRange = false
                }
            }
            if (inRange) {
                ans += matrix[i][j] + 1
            }
        }
    }
    return ans
}