package adventofcode2021

import java.io.File

private val workingDirPath = "${System.getProperty("user.dir")}"
private val pathToDataDir = "$workingDirPath//src/main/kotlin/adventofcode2021/data/"

/**
 * Get Data from TXT File and turn it into an int list
 * for days when data is just numbers (ex: day 1)
 *
 * @param day: Int - day of the code challenge
 */
fun getDataFromFileAsIntList(day: Int): List<Int> {
    val list = mutableListOf<Int>()
    File("${pathToDataDir}Day${day}Data.txt").forEachLine {
        list.add(it.toInt())
    }
    return list
}

/**
 * Get Data from TXT File and turn it into an string list
 * for days when data is string / command (ex: day 2)
 *
 * @param day: Int - day of the code challenge
 */
fun getDataFromFileAsStringList(day: Int): List<String> =
    File("${pathToDataDir}Day${day}Data.txt").useLines {
        it.toList()
    }