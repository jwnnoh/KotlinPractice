package demo.kotlinpractice.domain.member.presentation

import demo.kotlinpractice.domain.auth.AuthDetails
import demo.kotlinpractice.domain.member.presentation.dto.request.LoginRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.request.MemberUpdateRequest
import demo.kotlinpractice.domain.member.presentation.dto.response.LoginResponse
import demo.kotlinpractice.domain.member.presentation.dto.response.MemberResponse
import demo.kotlinpractice.domain.member.presentation.facade.MemberFacade
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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