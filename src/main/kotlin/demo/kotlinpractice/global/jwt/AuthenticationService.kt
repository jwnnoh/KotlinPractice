package demo.kotlinpractice.global.jwt

import demo.kotlinpractice.domain.auth.AuthDetailsService
import demo.kotlinpractice.domain.member.presentation.dto.request.LoginRequest
import demo.kotlinpractice.domain.member.presentation.dto.response.LoginResponse
import io.jsonwebtoken.Claims
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.Collections

@Service
class AuthenticationService(
    private val jwtProvider: JwtProvider,
    private val authDetailsService: AuthDetailsService,
    private val authenticationManager: AuthenticationManager
) {
    fun authenticate(request: LoginRequest): LoginResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.name,
                request.password
            )
        )

        val authDetails = authDetailsService.loadUserByUsername(request.name)

        val accessToken = jwtProvider.generateAccessToken(
            authDetails.getId(),
            authDetails.username,
            authDetails.authorities.toString()
        )

        return LoginResponse(authDetails.getId(), accessToken)
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Claims = jwtProvider.extractAllClaims(token)
        val authorities: Collection<GrantedAuthority?> = Collections.singletonList(SimpleGrantedAuthority("ROLE_USER"))

        return UsernamePasswordAuthenticationToken(jwtProvider.getAuthDetails(claims), "", authorities)
    }
}
