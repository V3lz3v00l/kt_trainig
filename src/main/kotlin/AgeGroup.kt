/*
    solution for kata
    https://www.codewars.com/kata/5829ca646d02cd1a65000284

    for test i formatted input array to simplify code
 */

class AgeGroup {

    val list1 = listOf<Int>(19, 29, 39, 40, 59, 60, 75, 88, 98, 128)

    fun isDiversed(list: List<Int>): Boolean {
        var testage = 10
        val resList = mutableListOf<Int>()
        for (i in list1) {
            if (i in testage..testage+10 && testage < 100) {
                resList.add(i)
                testage += 10
            } else if (testage > 100 && i < testage) {
                resList.add(i)
            }
        }
        return resList.size >= 9
    }
}