package demo.kotlinpractice.auth.service

import demo.kotlinpractice.auth.port.`in`.TokenUseCase
import demo.kotlinpractice.auth.port.out.TokenPort
import org.springframework.stereotype.Service

@Service
class TokenService(
    private val tokenPort: TokenPort
) : TokenUseCase {
    override fun generateAccessToken(
        id: Long,
        socialId: String,
        role: String
    ): String = tokenPort.generateAccessToken(
        id,
        socialId,
        role,
    )
}
