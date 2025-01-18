package demo.kotlinpractice.auth.dto.request

data class MemberCreateRequest(
    val name: String,
    val password: String,
)
