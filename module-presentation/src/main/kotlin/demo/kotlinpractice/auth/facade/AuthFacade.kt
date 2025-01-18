package demo.kotlinpractice.auth.facade

import demo.kotlinpractice.auth.port.`in`.AuthUseCase
import demo.kotlinpractice.auth.port.`in`.TokenUseCase
import demo.kotlinpractice.member.domain.Member
import demo.kotlinpractice.auth.dto.request.LoginRequest
import demo.kotlinpractice.auth.dto.request.MemberCreateRequest
import demo.kotlinpractice.auth.dto.response.LoginResponse
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.member.port.`in`.MemberUseCase
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AuthFacade(
    private val authUseCase: AuthUseCase,
    private val memberUseCase: MemberUseCase,
    private val tokenUseCase: TokenUseCase,
) {
    fun createMember(request: MemberCreateRequest): MemberResponse {
        val encodedPassword = authUseCase.encodePassword(request.password)

        return MemberResponse.of(
            memberUseCase.createMember(request.name, encodedPassword)
        )
    }

    fun loginMember(request: LoginRequest): LoginResponse {
        val memberId = authUseCase.loginMember(request.name, request.password)

        return getTokenResponse(memberId)
    }

    private fun getTokenResponse(memberId: Long): LoginResponse {
        val member: Member = memberUseCase.findById(memberId)

        val accessToken: String = tokenUseCase.generateAccessToken(
            member.id,
            member.name,
            "ROLE_USER",
        )

        return LoginResponse(memberId, accessToken)
    }
}
