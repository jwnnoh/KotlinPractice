package demo.kotlinpractice.global.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.jetbrains.annotations.NotNull
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthorizationFilter(
    private val authenticationService: AuthenticationService
): OncePerRequestFilter() {
    private val headerPrefix: String = "Authorization"
    private val tokenPrefix: String = "Bearer "

    override fun doFilterInternal(
        @NotNull request: HttpServletRequest,
        @NotNull response: HttpServletResponse,
        @NotNull filterChain: FilterChain
    ) {
        val accessToken: String? = extractToken(request)

        if (null != accessToken) {
            val authentication: Authentication = this.authenticationService.getAuthentication(accessToken)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun extractToken(request: HttpServletRequest): String? {
        val authorizationHeader: String? = request.getHeader(headerPrefix)

        if (null != authorizationHeader && authorizationHeader.startsWith(tokenPrefix)) {
            return authorizationHeader.substringAfter(tokenPrefix)
        }
        return null
    }
}
