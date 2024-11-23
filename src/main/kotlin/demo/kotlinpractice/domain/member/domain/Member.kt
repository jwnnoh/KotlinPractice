package demo.kotlinpractice.domain.member.domain

class Member(
    private val id: Long,
    private var name: String,
    private var password: String,
) {
    fun getId(): Long = id

    fun getName(): String = name

    fun getPassword(): String = password

    fun updateInfo(name: String, password: String): Unit {
        this.name = name
        this.password = password
    }
}
