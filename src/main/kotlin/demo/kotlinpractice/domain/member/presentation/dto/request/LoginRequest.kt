package demo.kotlinpractice.domain.member.presentation.dto.request

data class LoginRequest(
    val name: String,
    val password: String
)