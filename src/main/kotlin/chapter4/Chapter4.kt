package chapter4

import java.lang.IllegalArgumentException

fun main() {
    val charList = listOf('a', 'b', 'c')

    println(toString(charList))
    println(toStringCorec2(charList))
    println(sum1(10))
    println(sum2(10))
    println(sum1(listOf(1, 2, 3, 4)))
    println(sum2(listOf(1, 2, 3, 4)))
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

// 연습문제 4-2
val factorial: (Int) -> Int by lazy {
    { n ->
        if (n <= 1) n else n * factorial(n - 1)
    }
}

/**
 * 4.3 재귀 함수와 리스트
 */
fun <T> List<T>.head(): T =
    if (this.isEmpty())
        throw IllegalArgumentException("head called on empty list")
    else
        this[0]

fun <T> List<T>.tail(): List<T> =
    if (this.isEmpty())
        throw IllegalArgumentException("tail called on empty list")
    else
        this.drop(1)

fun sum1(list: List<Int>): Int =
    if (list.isEmpty())
        0
    else
        list.head() + sum1(list.tail())

fun sum2(list: List<Int>): Int {
    tailrec fun sumTail(list: List<Int>, acc: Int): Int =
        if (list.isEmpty())
            acc
        else
            sumTail(list.tail(), acc + list.head())
    return sumTail(list, 0)
}
