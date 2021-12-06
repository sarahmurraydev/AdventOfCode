package adventofcode2021

fun main() {
    println("=====================")
    println("Advent of Code Day 5!")
    val convertedData = convertDataStringToInt(realData) // convertDataStringToInt(exData)
    val days = 80
    println("After $days days we have ${haveFish(days, convertedData)}")
}

const val exData = "3,4,3,1,2"
const val realData = "5,1,2,1,5,3,1,1,1,1,1,2,5,4,1,1,1,1,2,1,2,1,1,1,1,1,2,1,5,1,1,1,3,1,1,1,3,1,1,3,1,1,4,3,1,1,4,1,1,1,1,2,1,1,1,5,1,1,5,1,1,1,4,4,2,5,1,1,5,1,1,2,2,1,2,1,1,5,3,1,2,1,1,3,1,4,3,3,1,1,3,1,5,1,1,3,1,1,4,4,1,1,1,5,1,1,1,4,4,1,3,1,4,1,1,4,5,1,1,1,4,3,1,4,1,1,4,4,3,5,1,2,2,1,2,2,1,1,1,2,1,1,1,4,1,1,3,1,1,2,1,4,1,1,1,1,1,1,1,1,2,2,1,1,5,5,1,1,1,5,1,1,1,1,5,1,3,2,1,1,5,2,3,1,2,2,2,5,1,1,3,1,1,1,5,1,4,1,1,1,3,2,1,3,3,1,3,1,1,1,1,1,1,1,2,3,1,5,1,4,1,3,5,1,1,1,2,2,1,1,1,1,5,4,1,1,3,1,2,4,2,1,1,3,5,1,1,1,3,1,1,1,5,1,1,1,1,1,3,1,1,1,4,1,1,1,1,2,2,1,1,1,1,5,3,1,2,3,4,1,1,5,1,2,4,2,1,1,1,2,1,1,1,1,1,1,1,4,1,5"

fun convertDataStringToInt(data: String): MutableList<Int> {
    val splitData = data.split(",")

    return splitData.map {
        it.toInt()
    }.toMutableList()
}

// every 7 days, existing lantern fish has new lantern fish
// you can figure out after X days, existing lantern fish should have had:
// y = (x - b)/7
// where b is the current timer of the lanternfish
// for new fish, b = 8

// every day X fish have timer of 0, the next day those X fish have timer of 6 & there are X new fish with timer of 8

fun haveFish(days: Int, fish: MutableList<Int>): Int{
    var daysToGo = days
    var numFish = fish.size
    while (daysToGo > 0) {
        for (f in fish.indices) {
            if (fish[f] > 0) {
                fish[f] -= 1
            } else {
                //println("fish $f had a fish!")
                // fish[f] = 0
                fish[f] = 6
                fish.add(8)
                numFish += 1
            }
        }
        daysToGo -= 1
    }

    return numFish
}