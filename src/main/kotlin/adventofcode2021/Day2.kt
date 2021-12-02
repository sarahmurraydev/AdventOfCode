package adventofcode2021

/**
 * Day 2: https://adventofcode.com/2021/day/2
 *
 * Answers:
 * Part 1: 1728414  // (d=786, h=2199)
 * Part 2: 1765720035 // d=802965, h=2199 -- makes sense depth is ton higher
 */
/**
 * Example Data:
 */
val testMoves = listOf<String>(
    "forward 5",
    "down 5",
    "forward 8",
    "up 3",
    "down 8",
    "forward 2"
)

val movesFromDataSet = getDataFromFileAsStringList(2)

fun main(){
    println("Advent of Code Day 2!")
    println("=====================")
    println("My sub starts at d=0, h=0")
    println("My sub makes ${movesFromDataSet.size} moves")
    // change which Sub class for part 1 or part 2 answer
    val (d, h) = getSubPositionAfterMoves(SubmarinePart2(), movesFromDataSet)
    println("My sub ends up at d=$d, h=$h")
    println("d x h = ${d*h}")
}

abstract class BaseSubmarine(var depth: Int = 0, var horizontal: Int = 0) {
    abstract fun moveDownN(n: Int)
    abstract fun moveUpN(n: Int)
    abstract fun moveForwardN(n: Int)
}

/**
 * Part 1 Class
 */
class Submarine(): BaseSubmarine() {

    override fun moveDownN(n: Int) {
        depth += n
    }

    override fun moveUpN(n: Int) {
        depth -= n
    }

    override fun moveForwardN(n: Int) {
        horizontal += n
    }
}

fun getSubPositionAfterMoves(sub:BaseSubmarine, moves: List<String>): Pair<Int, Int> {
    for (move in moves) {
        val numMoves = move.last().toString().toInt()
        // converting char to int with char.toInt gives ASCII value (ex: 5 --> 53)
        // so have to convert to string then int
        when(move.first().toString()) {
            "d" -> sub.moveDownN(numMoves)
            "u" -> sub.moveUpN(numMoves)
            "f" -> sub.moveForwardN(numMoves)
            else -> {
                // don't move
                println("you didn't make a proper move")
            }
        }
    }

    return Pair(sub.depth, sub.horizontal)
}

/**
 * Part 2:
 * down X increases your aim by X units.
 * up X decreases your aim by X units.
 * forward X does two things:
 *      It increases your horizontal position by X units.
 *      It increases your depth by your aim multiplied by X.
 */
class SubmarinePart2(var aim: Int = 0) : BaseSubmarine() {

    override fun moveDownN(n: Int) {
        aim += n
    }

    override fun moveUpN(n: Int) {
        aim -= n
    }

    override fun moveForwardN(n: Int) {
        horizontal += n
        depth += aim * n
    }
}
