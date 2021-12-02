import java.io.File

fun main(){
    val myList = getDataFromFile()
    println("Day 1 Advent of Code 2021")
    println("My List has ${myList.size} elements")
    val increases = checkListForIncreases(myList)
    println("My list increases item by item $increases times")

    println("====================================")
    println("Day 1, Part 2: Sets of 3")
    val threeIncreases = checkListSetsForIncreases(myList)
    println("My list increases sums of threes by $threeIncreases times")
}

/**
 * ex list:
 * val myList = listOf<Int>(0, 10, 5, 8, 6, 11, 14)
 *
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


/**
 * Part 2: Three sum window
 * want to compare sum(i, i+1, i+2) with sum(i+1, i+2, i+3)
 * we'll need to make two groups: i, i+1, i+2 and i+1, i+2, i+3
 * since the last element n = size - 1 = [(n-3) + 3] .. we'll want to stop at size - 4
 *
 * ex list:
 * val myList = listOf<Int>(0, 10, 5, 8, 6, 11, 14)
 * set1 = 0, 10, 5 ==> sum = 15
 * set2 = 10, 5, 8 ==> sum = 23 -- increased
 * set3 = 5, 8, 6 ==> sum = 19
 * set4 = 8, 6, 11 ==> sum = 25 -- increased
 * set5 = 6, 11, 14 ==> sum = 31 -- increased
 * ==> 3 increases
 * */
fun checkListSetsForIncreases(data: List<Int>): Int {
    var numIncreases = 0

    for(i in 0..data.size-4) {
        // val set1Sum = data[i] + data[i+1] + data[i+2]
        // val set2Sum = data[i+1] + data[i+2] + data[i+3]
        // set2Sum > set1Sum then becomes
        // data[i+1] + data[i+2] + data[i+3] > data[i] + data[i+1] + data[i+2]
        // which reduces to:
        // data[i+3] > data[i]
        if(data[i+3] > data[i]) {
            numIncreases += 1
        }
    }

    return numIncreases
}