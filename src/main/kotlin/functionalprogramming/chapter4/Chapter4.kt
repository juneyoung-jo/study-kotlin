package functionalprogramming.chapter4

import java.lang.IllegalArgumentException
import java.math.BigInteger

fun main() {
    val charList = listOf('a', 'b', 'c')

    println(toString(charList))
    println(toStringCorec2(charList))
    println(sum1(10))
    println(sum2(10))
    println(sum1(listOf(1, 2, 3, 4)))
    println(sum2(listOf(1, 2, 3, 4)))
    println(fib(5000))
    println(makeString(listOf("a", "b", "c"), ","))
    println(sum3(listOf(1, 2, 3, 4)))
    println(range(1, 4))
}

// 연습문제 4-12
fun range(start: Int, end: Int): List<Int> =
    if (start >= end) listOf()
    else prepend(range(start + 1, end), start)


fun <T> prepend(list: List<T>, elem: T): List<T> = listOf(elem) + list

// 연습문제 4-11
//fun range(start: Int, end: Int): List<Int> = unfold(start, { it + 1 }, { it < end })

// 연습문제 4-10
fun <T> unfold(seed: T, f: (T) -> T, p: (T) -> Boolean): List<T> {
    val result = mutableListOf<T>()
    var tmp = seed
    while (p(tmp)) {
        result.add(tmp)
        tmp = f(tmp)
    }
    return result
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


//fun prepend(c: Char, s: String) = "$c$s"

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

// 연습문제 4-3
fun fib(x: Int): BigInteger {
    tailrec fun fib(val1: BigInteger, val2: BigInteger, x: BigInteger): BigInteger =
        when {
            (x == BigInteger.ZERO) -> BigInteger.ONE
            (x == BigInteger.ONE) -> val1 + val2
            else -> fib(val2, val1 + val2, x - BigInteger.ONE)
        }
    return fib(BigInteger.ZERO, BigInteger.ONE, BigInteger.valueOf(x.toLong()))
}

// 연습문제 4-4
fun <T> makeString(list: List<T>, delim: String): String {
    tailrec fun makeString_(list: List<T>, acc: String): String =
        when {
            list.isEmpty() -> acc
            acc.isEmpty() -> makeString_(list.tail(), "${list.head()}")
            else -> makeString_(list.tail(), "$acc$delim${list.head()}")
        }
    return makeString_(list, "")
}

// 연습문제 4-5
fun <T, U> foldLeft(list: List<T>, z: U, func: (U, T) -> U): U {
    fun foldLeft(list: List<T>, acc: U): U =
        if (list.isEmpty())
            acc
        else
            foldLeft(list.tail(), func(acc, list.head()))
    return foldLeft(list, z)
}

fun sum3(list: List<Int>) = foldLeft(list, 0, Int::plus)

// 연습문제 4-6
fun <T, U> foldRight(list: List<T>, identity: U, func: (T, U) -> U): U =
    if (list.isEmpty())
        identity
    else
        func(list.head(), foldRight(list.tail(), identity, func))
