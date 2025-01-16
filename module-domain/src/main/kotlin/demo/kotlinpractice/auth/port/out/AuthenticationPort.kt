package demo.kotlinpractice.auth.port.out

interface AuthenticationPort {
    fun authenticate(name: String, password: String): Long
}
