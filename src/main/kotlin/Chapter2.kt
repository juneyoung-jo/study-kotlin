fun main() {

    /**
     * 2.16 변성
     */
    val s = "A String"
    val a: Any = s

    val ls = mutableListOf("A String")
    val la = ls + 42 // List<Any>로 추론

    val lla: MutableList<Any> = mutableListOf()
    addAll(lla, ls)

    // 2.16.2 공변성을 써야 하는 경우와 반공변성을 써야 하는 경우

}

interface Bag<T> {
    fun get(): T
}

fun <T> addAll(
    list1: MutableList<T>,
    list2: MutableList<out T> // T를 공변성을 만듦
) {
    for (elem in list2) list1.add(elem)
}
