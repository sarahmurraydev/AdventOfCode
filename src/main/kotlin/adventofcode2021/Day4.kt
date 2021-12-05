package adventofcode2021

fun main(){
    println("Advent of Code Day 3!")
    println("=====================")
    println("====== Example Data: =======")

    val value = gotBingo(exBingoCards, exNumbersCalled)
    println("score is $value")

    println("====== Actual Data: =======")
    //val day4Data = getDataFromFileAsStringList(4)

    println("============================================")
    println("====== PART 2 =======")
    println("============================================")
    println("====== Example Data: =======")
    println()
    println("====== Actual Data: =======")
    println()
    println("============================================")
}

// bingo
// need to keep track of what numbers have been called / marked on the board
// need to keep track of what numbers are needed to get bingo

// could loop through each matrix
// if matrix has number called, pop it off the array
// check that the matrix row has length > 0
// check that the matrix column has length > 0

val exNumbersCalled = listOf(7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1)

val exBingoCards = mutableListOf(
    mutableListOf(22, 13, 17, 11,  0),
    mutableListOf(8,  2, 23,  4, 24),
    mutableListOf(21,  9, 14, 16,  7),
    mutableListOf(6, 10,  3, 18,  5),
    mutableListOf(1, 12, 20, 15, 19),

    mutableListOf(3, 15,  0,  2, 22),
    mutableListOf(9, 18, 13, 17,  5),
    mutableListOf(19,  8,  7, 25, 23),
    mutableListOf(20, 11, 10, 24,  4),
    mutableListOf(14, 21, 16, 12,  6),

    mutableListOf(14, 21, 17, 24,  4),
    mutableListOf(10, 16, 15,  9, 19),
    mutableListOf(18,  8, 23, 26, 20),
    mutableListOf(22, 11, 13,  6,  5),
    mutableListOf(2,  0, 12,  3,  7)
)

fun gotBingo(bingoCards: MutableList<MutableList<Int>>, numbersCalled: List<Int>): Int? {
    for (num in numbersCalled) {
        var rowSum = 0
        var colSum = 0
        var sumOfRows = MutableList(5) { 0 }
        for (row in 0..4) {
            for (col in 0..4) {
                if (bingoCards[row][col] == numbersCalled[num]) {
                    bingoCards[row][col] = 100
                }

                // after element has been set to 100, check what the sum of the row is
                rowSum += bingoCards[row][col]
                colSum += bingoCards[col][row]

                //println("row sum: $rowSum")

                if (rowSum == 500 || colSum == 500) {
                    println("we got bingo!")
                    println("In card number ${(row % 5) + 1}")
                    println("On number ${numbersCalled[num]}")
                    println("It took ${num + 1 } numbers to be called before we got bingo")
                    //println("Winning card:")
                    val numsCalledSubSet = numbersCalled.subList(0, num+1)
                    val sum = getSumOfWinningCard(row % 5, bingoCards, numsCalledSubSet)
                    return sum * numbersCalled[num]
                }
            }
            // now get the sum of all the unmarked numbers on the board
            // this should be a sum of row sums mod 100
            sumOfRows[row] = rowSum
        }
    }
    return null
}

fun getSumOfWinningCard(cardNum: Int?, bingoCards: MutableList<MutableList<Int>>, numbersCalled: List<Int>): Int {
    if(cardNum == null) return 0

    val startingRow = cardNum * 5
    val endingRow = startingRow + 4

    var sum = 0

    println("============")

    for (i in startingRow..endingRow) {
        //println(bingoCards[i])
        for (j in 0..4) {
            val ele = bingoCards[i][j]
            if(!numbersCalled.contains(ele)) {
                sum += ele
            }
        }
    }
    println("============")
    println("sum: $sum")
    return sum
}

