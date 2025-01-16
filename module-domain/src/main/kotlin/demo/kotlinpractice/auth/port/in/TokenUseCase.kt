package demo.kotlinpractice.auth.port.`in`

interface TokenUseCase {
    fun generateAccessToken(
        id: Long,
        socialId: String,
        role: String,
    ): String
}
