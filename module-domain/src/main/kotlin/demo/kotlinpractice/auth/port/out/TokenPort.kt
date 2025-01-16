package demo.kotlinpractice.auth.port.out

interface TokenPort {
    fun generateAccessToken(
        id: Long,
        socialId: String,
        role: String,
    ): String
}