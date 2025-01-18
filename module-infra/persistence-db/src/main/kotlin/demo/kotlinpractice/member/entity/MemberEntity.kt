package demo.kotlinpractice.member.entity

import demo.kotlinpractice.member.domain.Member
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity(name = "member")
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long = 0,

    @Column(nullable = false)
    private val name: String,

    @Column(nullable = false)
    private val password: String
) {
    fun toDomain(): Member {
        return Member(
            id = id,
            name = name,
            password = password
        )
    }

    companion object {
        fun from(member: Member): MemberEntity {
            return MemberEntity(
                id = member.id,
                name = member.name,
                password = member.password,
            )
        }
    }

    fun getId(): Long = id

    fun getName(): String = name

    fun getPassword(): String = password
}