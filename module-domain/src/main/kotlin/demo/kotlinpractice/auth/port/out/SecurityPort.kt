package demo.kotlinpractice.auth.port.out

interface SecurityPort {
    fun encode(password: String): String
}
