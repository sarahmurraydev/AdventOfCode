package adventofcode2021

import java.io.File

fun main(){
    println("Advent of Code Day 3!")
    println("=====================")
    println("====== Example Data: =======")

    //val value = gotBingo(exBingoCards, exNumbersCalled)
    //println("score is $value")
    println(callNumbers(exNumbersCalled, chunkedBingoCards as MutableList<MutableList<MutableList<Int>>>))

    println("====== Actual Data: =======")
    //val day4Data = getDataFromFileAsStringList(4)
    //println(gotBingo(data, dataNumsCalled))
    println(callNumbers(dataNumsCalled, data as MutableList<MutableList<MutableList<Int>>>))

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

val dataNumsCalled = listOf(
    26,38,2,15,36,8,12,46,88,72,32,35,64,19,5,66,20,52,74,3,59,94,45,56,0,6,67,24,97,50,92,93,84,65,71,90,96,21,87,75,
    58,82,14,53,95,27,49,69,16,89,37,13,1,81,60,79,51,18,48,33,42,63,39,34,62,55,47,54,23,83,77,9,70,68,85,86,91,41,
    4,61,78,31,22,76,40,17,30,98,44,25,80,73,11,28,7,99,29,57,43,10
)

val data = dataIntoMutableMatrix(4).chunked(5)

// ==================================================
/// starting over ... im confused

val chunkedBingoCards = exBingoCards.chunked(5)

fun callNumbers(nums: List<Int>, bingoCards: MutableList<MutableList<MutableList<Int>>>): String {
    val cardHasBingo = MutableList(bingoCards.size) { false }
    for (num in nums) {
        for (cardNum in bingoCards.indices) {
            val checkedBingo = checkBingoCardForNumber(bingoCards[cardNum], num, cardNum)
            if(checkedBingo.second) {
                //println("got bingo on card $cardNum!")
                // val sumOfWinningCard = getSumOfCard(cardNum, bingoCards[cardNum])
                // val score = sumOfWinningCard * num
                cardHasBingo[cardNum] = true
                //println(cardHasBingo)
            }
            //println("After number $num was called, ${cardHasBingo.count { it == true}} cards have bingo out of ${bingoCards.size}")

            if (!cardHasBingo.contains(false)) {
                // all the cards now have bingo
                println("the last card to get bingo was $cardNum when $num was called")
                val sumOfWinningCard = getSumOfCard(cardNum, bingoCards[cardNum])
                val score = sumOfWinningCard * num
                return "Card $cardNum has bingo with a score of $score"
                // got: Card 64 has bingo with a score of 11840 -- too high
            }
        }
    }

    return "there was no bingo"
}

fun checkBingoCardForNumber(
    card: MutableList<MutableList<Int>>,
    num: Int,
    cardNum: Int
): Pair<MutableList<MutableList<Int>>, Boolean>{
    for (r in card.indices) {
        var rowSum = 0
        var colSum = 0
        for (c in card[r].indices) {
            if (card[r][c] == num) {
                card[r][c] = -1
            }
            rowSum += card[r][c]
            colSum += card[c][r]
        }

        if(checkForBingo(rowSum)) {
            println("row $r of card $cardNum has bingo when number $num is called")
            return Pair(card, true)
        }

        if(checkForBingo(colSum)) {
            println("col has bingo")
            return Pair(card, true)
        }
    }

    return Pair(card, false)
}

fun checkForBingo(sum: Int): Boolean {
    return (sum == -5)
}

fun getSumOfCard(cardNum: Int, winningBingoCard: MutableList<MutableList<Int>>): Int {
    var sum = 0
    for (i in winningBingoCard.indices) {
        //println(bingoCards[i])
        for (j in 0..4) {
            val ele = winningBingoCard[i][j]
            if(ele != -1) {
                sum += ele
            }
        }
    }

    println("sum of winning card is $sum")

    return sum
}

