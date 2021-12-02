import java.io.File

fun main(){
    val myList = getDataFromFile()
    println("Day 1 Advent of Code 2021")
    println("My List has ${myList.size} elements")
    val increases = checkListForIncreases(myList)
    println("My list increases item by item $increases times")
}

/**
 * solution:
 * 0 --> 10 increased,
 * 10 --> 5 decreased,
 * 5 ---> 8 increased,
 * 8 ---> 6 decreased,
 * 6 --> 11 increased,
 * 11 --> 14 increased
 * ==> 4 increases
  */

/**
 * WANT: take in n, compare to n+1 item of list
 * if (n+1 > n) +1 to increased value
 * else do nothing
 */

fun checkListForIncreases(data: List<Int>): Int {
    var numOfIncreases = 0

    for (i in 0..data.size - 2) {
        // range should be from first element to second to last
        // for second to last element, i+1 will be last element
        if (data[i + 1] > data[i]) {
            numOfIncreases += 1
        }
    }

    return numOfIncreases
}

/**
 * Get Data from TXT File
 */
fun getDataFromFile(): List<Int> {
    val path = "${System.getProperty("user.dir")}"
    val list = mutableListOf<Int>()
    File("$path/src/main/kotlin/adventofcode2021/Day1Data.txt").forEachLine {
        list.add(it.toInt())
    }

    return list
}
