/**
 * Given an array of Int's, that continuously raises to vertex, and then fades
 * Need to find that vertex(index) via binary search.
 */

class BinarySearch {
    //10
    val array = listOf<Int>(1, 3, 6, 7, 8, 9, 13, 16, 19, 24, 33, 32, 25, 13, 7, 4, 2)

    fun findVertexIndex(source: List<Int>, primal: List<Int>): Int {
        val mid: Int = source[source.size / 2]
        val prev: Int = source[source.size / 2 - 1]
        val next: Int = source[source.size / 2 + 1]

        val prevArray = source.slice(0..source.indexOf(mid))
        val nextArray = source.slice(source.indexOf(mid)..source.lastIndex)

        return when (mid) {
            in (prev + 1) until next -> findVertexIndex(nextArray, primal)
            in (next + 1) until prev -> findVertexIndex(prevArray, primal)
            else -> primal.indexOf(mid)
        }
    }

    fun findVertexIndexIterate(source: List<Int>): Int {
        return source.indexOf(source.maxOrNull()!!)
    }
}