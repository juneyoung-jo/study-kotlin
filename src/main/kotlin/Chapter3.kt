fun main() {

    // 3.2.8 함수의 합성
    val squareOfTriple1 = compose1(::square, ::triple)
    println("squareOfTriple1: ${squareOfTriple1(2)}") // 36

    // 3.3.2 커리한 함수 적용하기
    println(add(3)(5)) // 8

    // 3.3.3 고차 함수 구현하기
    println("squareOfTriple2: ${squareOfTriple2(2)}") // 36

}


fun square(n: Int) = n * n
fun triple(n: Int) = n * 3


// 연습문제 3-1
fun compose1(
    f: (Int) -> Int,
    g: (Int) -> Int
): (Int) -> Int = { x -> f(g(x)) }

// 연습문제 3-2
fun <T, U, V> compose2(
    f: (U) -> V,
    g: (T) -> U
): (T) -> V = { x -> f(g(x)) }

// 연습문제 3-3
val add: (Int) -> (Int) -> Int = { a -> { b -> a + b } }

// 연습문제 3-4
val square: (Int) -> Int = { x -> x * x }
val triple: (Int) -> Int = { x -> x * 3 }
val compose3: ((Int) -> Int) -> ((Int) -> Int) -> (Int) -> Int =
    { x -> { y -> { z -> x(y(z)) } } }
val squareOfTriple2 = compose3(square)(triple)
