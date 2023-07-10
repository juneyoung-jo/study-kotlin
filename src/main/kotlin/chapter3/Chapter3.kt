package chapter3

fun main() {

    // 3.2.8 함수의 합성
    val squareOfTriple1 = compose1(::square, ::triple)
    println("squareOfTriple1: ${squareOfTriple1(2)}") // 36

    // 3.3.2 커리한 함수 적용하기
    println(add(3)(5)) // 8

    // 3.3.3 고차 함수 구현하기
    println("squareOfTriple2: ${squareOfTriple2(2)}") // 36

    // 3.3.5 익명 함수 사용하기
    val cos = higherCompose<Double, Double, Double>()({ x: Double -> Math.PI / 2 - x })(Math::sin)
    println("cos(2.0): ${cos(2.0)}")
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
    { x ->
        { y ->
            { z ->
                x(y(z))
            }
        }
    }
val squareOfTriple2 = compose3(square)(triple)

// 연습문제 3-5
fun <T, U, V> higherCompose(): ((U) -> V) -> ((T) -> U) -> (T) -> V =
    { f ->
        { g ->
            { x ->
                f(g(x))
            }
        }
    }

val squareOfTriple3 = higherCompose<Int, Int, Int>()(square)(triple)

// 연습문제 3-6
fun <T, U, V> higherAndThen(): ((T) -> U) -> ((U) -> V) -> (T) -> V =
    { f ->
        { g ->
            { x ->
                g(f(x))
            }
        }
    }

// 3.3.6 로컬 함수 정의하기
fun cos(arg: Double): Double {
    fun f(x: Double): Double = Math.PI / 2 - x
    fun sin(x: Double): Double = Math.sin(x)
    return compose2(::f, ::sin)(arg)
}

// 연습문제 3-7
fun <A, B, C> partialA(a: A, f: (A) -> (B) -> C): (B) -> C = f(a)

// 연습문제 3-8
fun <A, B, C> partialB(b: B, f: (A) -> (B) -> C): (A) -> C = { a: A -> f(a)(b) }

// 연습문제 3-9
fun <A, B, C, D> curried(): (A) -> (B) -> (C) -> (D) -> String = { a ->
    { b ->
        { c ->
            { d ->
                "$a, $b, $c, $d"
            }
        }
    }
}

// 연습문제 3-10
fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C =
    { a ->
        { b ->
            f(a, b)
        }
    }

