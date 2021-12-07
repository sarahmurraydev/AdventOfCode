package adventofcode2021

fun main(){
    println("Advent of Code Day 3!")
    println("=====================")
    println("====== Example Data: =======")
    println(convertBitPairToDecimalProduct(getMostCommonBit(bits)))

    println("====== Actual Data: =======")
    val day3Data = getDataFromFileAsStringList(3)
    //println(convertBitPairToDecimalProduct(getMostCommonBit(day3Data)))

    println("============================================")
    println("====== PART 2 =======")
    println("============================================")
    println("====== Example Data: =======")
    val exPair = findOAndCoRatings(bits)
    println(convertBitPairToDecimalProduct(exPair))
    println("====== Actual Data: =======")
    val pair = findOAndCoRatings(day3Data)
    println(convertBitPairToDecimalProduct(pair))
    println("============================================")
}

val bits = listOf(
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111",
    "00111",
    "11100",
    "10000",
    "11001",
    "00010",
    "01010"
)

// first data set CO2 rating is right without even trying? so make another test set
val secondExData = listOf(
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111"
)
// sums: 4, 2, 6, 4, 3 ==> 1, 0, 1, 1, 1 = gammma
// oxygen: "10111"
// co2:  "00100",

val thirdExData = listOf(
    "00100",
    "01110",
    "10010",
    "00101",
    "10001",
    "01100"
)
// sums: 2, 2, 4, 2, 2 ==> 0, 0, 1, 0, 0 = gammma
// oxygen: "01110"
// co2:  "",

/**
 * gamma rate = most common bits converted to decimal
 * epsilon rate = least common bit converted to decimal or opposite of gamma
 * power consumption = gamma * epislon
 */

// for each item in bits, make an array of first elements, second elements, etc ..
// for each item in bits, sum the i-th elements
// if the sum > bits.size/2
// then 1 is more common
// else 0 is more common
fun getMostCommonBit(data: List<String>): Pair<String, String> {
    // make a list of the number of sums
    val lengthOfBits = data[0].length
    val sums = MutableList(lengthOfBits) { 0 }
    var gamma = ""
    var epsilon = ""
    var currentMostCommonDig = ""
    var currentleastCommonDig = ""

    for (j in 0 until lengthOfBits) {
        // question today: how to split kotlin string into individual elements
        // ".split("") turned the bit into an array of
        // [, 0, 0, 1, 0, 0, ]
        for (i in data.indices) {
            val bit = data[i]
            // data[i].length should always be 4 but we'll just make is scalable
            // if the bits were of different lengths
            // println(bit[j])
            // that tricky char sequence strikes again...
            // have to convert to string then int
            sums[j] += bit[j].toString().toInt()
        }

        println("the sum for the $j-th digit is ${sums[j]}")

        // once we've gone through all the bits, we have sum[j] done
        if (sums[j] < (data.size/2)) {
            // zero is most common
            currentMostCommonDig = "0"
            currentleastCommonDig = "1"
        } else {
            // one is most common or 0 & 1 appear same num times
            currentMostCommonDig = "1"
            currentleastCommonDig = "0"
        }

        gamma += currentMostCommonDig
        epsilon += currentleastCommonDig

    }

    println("in bits, my gamma rate is $gamma")
    println("in bits, my epsilon rate is $epsilon")

    return Pair(gamma, epsilon)
}

fun convertBitPairToDecimalProduct(pair: Pair<String, String>): Int =
    pair.first.toInt(2) * pair.second.toInt(2)

/**
 * Part 2:
 *
 * oxygen rating - last number after going digit by digit and eliminating
 *  bits that don't have the most common digit value
 *
 * C02 scrubber rating -
 * last number going bit by bit that don't have least common digit value each time
 * */
fun findOAndCoRatings(data: List<String>): Pair<String, String> {
    val lengthOfBits = data[0].length
    val sums = MutableList(lengthOfBits) { 0 }
    var currentMostCommonDig = ""
    var currentleastCommonDig = ""
    var oxygenSet = data    // set of most common digit numbers
    var coTwoSet = data     // set of least common digit numbers

    for (j in 0 until lengthOfBits) {
        //println("for the ${j+1} digit, there are these elements left: $oxygenSet")
        for (i in oxygenSet.indices) {
            val bit = oxygenSet[i]
           //println("bit: $bit")
            //println("int: ${bit[j].toString().toInt()}")
            sums[j] += bit[j].toString().toInt()
            //println("sum: ${sums[j]}")
        }
        //println("the ${j+1} digit sum is ${sums[j]} out of ${oxygenSet.size} items")

        // once we've gone through all the bits, we have sum[j] done
        //println("is zero most common? ${sums[j] * 2 < oxygenSet.size}")
        //println("${sums[j] * 2} < ${oxygenSet.size}")
        // TODO: learn float vs int --
        // that messed me up here:
        // 3 < 3
        currentMostCommonDig = if (sums[j] * 2 < oxygenSet.size) {
            // zero is most common
            "0"
        } else {
            // one is most common or 0 & 1 appear same num times
            "1"
        }

        // part two -- now you have the sum but digit
        // and you know which one is more common
        // now, you want to remove all the elements from data that
        // don't match the current most common set

        if (oxygenSet.size > 1) {
            oxygenSet = oxygenSet.filter { it[j].toString() == currentMostCommonDig }
        } else {
            break
        }
    }

    // do the same thing for CO2
    val sumsC = MutableList(lengthOfBits) { 0 }
    for (j in 0 until lengthOfBits) {
        //println("for the ${j + 1} digit, there are these elements left: $coTwoSet")
        for (i in coTwoSet.indices) {
            val bit = coTwoSet[i]
            sumsC[j] += bit[j].toString().toInt()
        }

        // once we've gone through all the bits, we have sum[j] done
        currentleastCommonDig = if (sumsC[j] * 2 < coTwoSet.size) {
            // zero is most common
            "1"
        } else {
            // one is most common or 0 & 1 appear same num times
            "0"
        }

        if (coTwoSet.size > 1) {

            coTwoSet = coTwoSet.filter { it[j].toString() == currentleastCommonDig }
        } else {
            break
        }
    }

    if (coTwoSet.size == 1 && oxygenSet.size == 1) {
        return Pair(oxygenSet[0], coTwoSet[0])
    } else {
        return Pair("oppsie", "whoopsie")
    }
}