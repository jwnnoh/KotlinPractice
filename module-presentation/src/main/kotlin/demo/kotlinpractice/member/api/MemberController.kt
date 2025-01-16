package demo.kotlinpractice.member.api

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberFacade: MemberFacade,
) {
    @PostMapping
    fun createMember(@RequestBody request: MemberCreateRequest):
            ResponseEntity<MemberResponse> {

        return ResponseEntity.ok(memberFacade.createMember(request))
    }

    @GetMapping("/info")
    fun findMember(@AuthenticationPrincipal authDetails: AuthDetails):
            ResponseEntity<MemberResponse> {

        return ResponseEntity.ok(memberFacade.findMember(authDetails))
    }

    @PatchMapping
    fun updateMember(@RequestBody request: MemberUpdateRequest):
            ResponseEntity<MemberResponse> {

        return ResponseEntity.ok(memberFacade.updateMember(request))
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest):
            ResponseEntity<LoginResponse> {

        return ResponseEntity.ok(memberFacade.loginMember(request))
    }
}