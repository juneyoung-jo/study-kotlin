package chapter4

fun main() {
    val charList = listOf('a', 'b', 'c')

    println(toString(charList))
    println(toStringCorec2(charList))
    println(sum1(10))
    println(sum2(10))
}

/**
 * 재귀
 * 장점: 쉬움
 * 단점: 메모리낭비(중간단계 저장), 스택
 */
fun sum1(n: Int): Int = if (n < 1) 0 else n + sum1(n - 1)
fun sum2(n: Int): Int {
    tailrec fun sum(s: Int, i: Int): Int =
        if (i > n) s else sum(s + i, i + 1)
    return sum(0, 0)
}

// 공재귀: 한 단계의 출력을 다음 단계의 입력으로 사용하는 계산 단계를 합성
fun append(s: String, c: Char): String = "$s$c"
fun toString(list: List<Char>): String {
    tailrec fun toString(list: List<Char>, s: String): String =
        if (list.isEmpty())
            s
        else
            toString(list.subList(1, list.size), append(s, list[0]))
    return toString(list, "")
}

// 공재귀를 loop 로 변경
fun toStringCorec2(list: List<Char>): String {
    var s = ""
    for (c in list) s = append(s, c)
    return s
}


fun prepend(c: Char, s: String) = "$c$s"


