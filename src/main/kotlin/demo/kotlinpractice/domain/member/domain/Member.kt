package demo.kotlinpractice.domain.member.domain

class Member(
    private val id: Long,
    private val name: String,
    private val password: String,
) {
    fun getId(): Long = id

    fun getName(): String = name

    fun getPassword(): String = password
}
