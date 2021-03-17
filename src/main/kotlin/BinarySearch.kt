/**
 * Given an array of Int's, that continuously raises to vertex, and then fades
 * Need to find that vertex(index) via binary search.
 */

class BinarySearch {
    //10
    val array = listOf<Int>(1, 3, 6, 7, 8, 9, 13, 16, 19, 24, 33, 32, 25, 13, 7, 4, 2)

    fun findVertexIndex(source: List<Int>, primal: List<Int>): Int {
        return when (val mid: Int = source[source.size / 2]) {
            in (source[source.size / 2 - 1] + 1) until source[source.size / 2 + 1] ->
                findVertexIndex(source.slice(source.indexOf(mid)..source.lastIndex), primal)
            in (source[source.size / 2 + 1] + 1) until source[source.size / 2 - 1] ->
                findVertexIndex(source.slice(0..source.indexOf(mid)), primal)
            else -> primal.indexOf(mid)
        }
    }

    fun findVertexIndexIterate(source: List<Int>): Int {
        return source.indexOf(source.maxOrNull()!!)
    }
}