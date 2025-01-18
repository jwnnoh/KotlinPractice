package demo.kotlinpractice.auth.dto.response

data class LoginResponse(
    val memberId: Long,
    val accessToken: String
)
