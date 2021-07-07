import kotlinx.coroutines.*
import java.util.*

fun main(args: Array<String>) {
//    val bin = BinarySearch()
//    println(bin.findVertexIndexIterate(bin.array))
//    println(bin.findVertexIndex(bin.array, bin.array))

    val sort = Sort()
    var array = sort.generateUnsortedInts()
    print("before sort")
    println(array)
    sort.qSort(array, 0, array.size - 1)
    print("after sort")
    println(array)
}