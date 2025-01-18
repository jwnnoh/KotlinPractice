package demo.kotlinpractice.jwt

import demo.kotlinpractice.auth.port.out.AuthenticationPort
import demo.kotlinpractice.principal.service.AuthDetailsService
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
) : AuthenticationPort {
    override fun authenticate(name: String, password: String): Long {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                name,
                password
            )
        )
        val authDetails = authDetailsService.loadUserByUsername(name)

        return authDetails.getId()
    }

    fun getAuthentication(token: String): Authentication {
        val claims: Claims = jwtProvider.extractAllClaims(token)
        val authorities: Collection<GrantedAuthority?> = Collections.singletonList(SimpleGrantedAuthority("ROLE_USER"))

        return UsernamePasswordAuthenticationToken(jwtProvider.getAuthDetails(claims), "", authorities)
    }
}
