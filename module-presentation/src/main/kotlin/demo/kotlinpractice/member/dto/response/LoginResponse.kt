package demo.kotlinpractice.member.dto.response

data class LoginResponse(
    val memberId: Long,
    val accessToken: String
)