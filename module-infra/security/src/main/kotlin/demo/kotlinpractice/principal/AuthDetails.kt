package demo.kotlinpractice.principal

import java.util.Collections

data class AuthDetails(
    private val id: Long,
    private val name: String,
    private val password: String,
    private val role: String
): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return Collections.singletonList(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String {
        return password
    }

    override fun getUsername(): String {
        return name
    }

    fun getId(): Long {
        return id
    }
}