package demo.kotlinpractice.jwt

import demo.kotlinpractice.domain.auth.AuthDetails
import demo.kotlinpractice.domain.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.Base64
import java.util.Date
import javax.crypto.spec.SecretKeySpec
import kotlin.time.Duration
import kotlin.time.Duration.Companion.days

@Service
class JwtProvider(
    private val authDetailsService: AuthDetailsService,
    @Value("\${jwt.secret}") private val secret: String
) {
    private val accessTokenDuration: Duration = 1.days

    private val secretKey: SecretKeySpec
        get() {
            val keyBytes: ByteArray = Base64.getDecoder().decode(secret)
            return SecretKeySpec(keyBytes, "HmacSHA256")
        }

    fun generateAccessToken(memberId: Long, name: String, role: String): String {
        return generateToken(memberId, name, accessTokenDuration, role)
    }

    private fun generateToken(
        memberId: Long,
        name: String,
        duration: Duration,
        role: String
    ): String {
        val now = Date()
        val expiry = Date(now.time + duration.inWholeMilliseconds)

        return Jwts.builder()
            .subject(name)
            .claim("id", memberId)
            .claim("role", role)
            .expiration(expiry)
            .signWith(secretKey)
            .compact()
    }

    fun extractAllClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload()
    }

    fun getAuthDetails(claims: Claims): AuthDetails {
        return authDetailsService.loadUserByUsername(claims.subject)
    }
}
