package demo.kotlinpractice.member.api

import demo.kotlinpractice.member.dto.request.MemberUpdateRequest
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.member.facade.MemberFacade
import demo.kotlinpractice.principal.AuthDetails
import demo.kotlinpractice.response.ApiResponse
import demo.kotlinpractice.type.SuccessCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    private val memberFacade: MemberFacade,
) {
    @GetMapping("/info")
    fun findMember(@AuthenticationPrincipal authDetails: AuthDetails):
            ResponseEntity<ApiResponse<MemberResponse>> {

        return ResponseEntity.ok(
            ApiResponse.success(
                SuccessCode.REQUEST_OK, memberFacade.findMember(authDetails)
            )
        )
    }

    @PatchMapping
    fun updateMember(@RequestBody request: MemberUpdateRequest):
            ResponseEntity<ApiResponse<MemberResponse>> {

        return ResponseEntity.ok(
            ApiResponse.success(
                SuccessCode.REQUEST_OK, memberFacade.updateMember(request)
            )
        )
    }
}
