package demo.kotlinpractice.member.facade

@Service
class MemberFacade(
    private final val memberUseCase: MemberUseCase
) {
    fun createMember(request: MemberCreateRequest): MemberResponse {
        val member: Member = memberUseCase.createMember(request)

        return MemberResponse.of(member)
    }

    fun findMember(authDetails: AuthDetails): MemberResponse {
        val member: Member = memberUseCase.findMember(authDetails.getId())

        return MemberResponse.of(member)
    }

    fun updateMember(request: MemberUpdateRequest): MemberResponse {
        val member: Member = memberUseCase.updateMember(request)
        return MemberResponse.of(member)
    }

    fun loginMember(request: LoginRequest): LoginResponse {
        return memberUseCase.loginMember(request)
    }
}