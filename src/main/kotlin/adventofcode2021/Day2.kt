package adventofcode2021

/**
 * Day 2: https://adventofcode.com/2021/day/2
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
    val (d, h) = getSubPositionAfterMoves(movesFromDataSet)
    println("My sub ends up at d=$d, h=$h")
    println("d x h = ${d*h}")
}

private class Submarine(var depth: Int = 0, var horizontal: Int = 0) {

    fun moveDownN(n: Int) {
        depth += n
    }

    fun moveUpN(n: Int) {
        depth -= n
    }

    fun moveForwardN(n: Int) {
        horizontal += n
    }
}

fun getSubPositionAfterMoves(moves: List<String>): Pair<Int, Int> {
    val sub = Submarine()

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





