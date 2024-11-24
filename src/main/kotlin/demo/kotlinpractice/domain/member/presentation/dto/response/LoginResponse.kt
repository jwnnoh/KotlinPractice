package demo.kotlinpractice.domain.member.presentation.dto.response

data class LoginResponse(
    val memberId: Long,
    val accessToken: String
)