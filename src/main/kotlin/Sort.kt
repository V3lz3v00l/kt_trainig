import kotlin.random.Random as Random1

/**
 * Sorting elements of array with several algorithms
 */
class Sort {
    fun generateUnsortedInts(): MutableList<Int> {
        return MutableList(30) { kotlin.random.Random.nextInt(0, 100) }
    }

    fun qSort(array: MutableList<Int>, low: Int, high: Int) {
        var i = low
        var j = high

        val tmpArrayEl = array[low + (high - low) / 2]

        while (i <= j) {
            while (array[i] < tmpArrayEl) i++
            while (array[j] > tmpArrayEl) j--
            if (i <= j) {
                val temp: Int = array[i]
                array[i] = array[j]
                array[j] = temp
                i++
                j--
            }
        }

        if (low < j) qSort(array, low, j)
        if (i < high) qSort(array, i, high)
    }

}