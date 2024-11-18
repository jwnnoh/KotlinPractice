package demo.kotlinpractice.domain.member.presentation

import demo.kotlinpractice.domain.member.presentation.dto.request.MemberCreateRequest
import demo.kotlinpractice.domain.member.presentation.dto.response.MemberResponse
import demo.kotlinpractice.domain.member.presentation.facade.MemberFacade
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberFacade: MemberFacade
) {
    @PostMapping
    fun createMember(@RequestBody request: MemberCreateRequest):
            ResponseEntity<MemberResponse> {
        return ResponseEntity.ok(memberFacade.createMember(request))

    }
}