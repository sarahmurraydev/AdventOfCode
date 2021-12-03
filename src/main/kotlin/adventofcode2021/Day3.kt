package adventofcode2021

fun main(){
    println("Advent of Code Day 3!")
    println("=====================")
    println("====== Example Data: =======")
    println(getMostCommonBit(bits))

    println("====== Actual Data: =======")
    val day3Data = getDataFromFileAsStringList(3)
    println(getMostCommonBit(day3Data))
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
fun getMostCommonBit(data: List<String>): Int {
    // make a list of the number of sums
    val lengthOfBits = data[0].length
    val sums = MutableList(lengthOfBits) { 0 }
    var gamma = ""
    var epsilon = ""

    for (i in data.indices) {
        val bit = data[i]
        // question today: how to split kotlin string into individual elements
        // ".split("") turned the bit into an array of
        // [, 0, 0, 1, 0, 0, ]
        for (j in 0 until lengthOfBits) {
            // data[i].length should always be 4 but we'll just make is scalable
            // if the bits were of different lengths
            //println(bit[j])
            // that tricky char sequence strikes again...
            // have to convert to string then int
            sums[j] += bit[j].toString().toInt()
        }
    }

    println("The sums are now: $sums")

    for (s in sums.indices) {
        if (sums[s] < (data.size/2)) {
            // zero is most common
            gamma += "0"
            epsilon += "1"
        } else {
            // one is most common
            gamma += "1"
            epsilon += "0"
        }
    }

    println("in bits, my gamma rate is $gamma")
    println("in bits, my epsilon rate is $epsilon")

    return gamma.toInt(2) * epsilon.toInt(2)
}