package demo.kotlinpractice.member.domain

class Member(
    val id: Long,
    var name: String,
    var password: String,
) {
    fun updateInfo(name: String, password: String): Unit {
        this.name = name
        this.password = password
    }
}
