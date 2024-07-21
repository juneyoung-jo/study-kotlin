
// 봉인된 인터페이스 정의
sealed interface Error

// 봉인된 인터페이스 Error를 구현해는 봉인된 클래스 생성
sealed class IOError(): Error

// 봉인된 클래스 'IOError' 를 확장(상속)하는 하위 클래스 정의
class FileReadError(val file: String): IOError()
class DatabaseError(val source: String): IOError()

// 봉인된 인터페이스 'Error'를 구현하는 싱글톤 객체 생성
object RuntimeError: Error
