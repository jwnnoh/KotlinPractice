package demo.kotlinpractice.auth.port.`in`

interface AuthUseCase {
    fun encodePassword(password: String): String

    fun loginMember(name: String, password: String): Long
}
