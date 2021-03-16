import kotlinx.coroutines.*

fun main(args: Array<String>) {
    val bin = BinarySearch()
    println(bin.findVertexIndexIterate(bin.array))
    println(bin.findVertexIndex(bin.array, bin.array))

}