package demo.kotlinpractice.auth.api

import demo.kotlinpractice.auth.facade.AuthFacade
import demo.kotlinpractice.auth.dto.request.LoginRequest
import demo.kotlinpractice.auth.dto.request.MemberCreateRequest
import demo.kotlinpractice.auth.dto.response.LoginResponse
import demo.kotlinpractice.member.dto.response.MemberResponse
import demo.kotlinpractice.response.ApiResponse
import demo.kotlinpractice.type.SuccessCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(
    private val authFacade: AuthFacade,
) {
    @PostMapping("/register")
    fun createMember(@RequestBody request: MemberCreateRequest):
            ResponseEntity<ApiResponse<MemberResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                SuccessCode.REQUEST_OK, authFacade.createMember(request)
            )
        )

    @PostMapping("/login")
    fun loginMember(@RequestBody request: LoginRequest):
            ResponseEntity<ApiResponse<LoginResponse>> =
        ResponseEntity.ok(
            ApiResponse.success(
                SuccessCode.LOGIN_SUCCESS, authFacade.loginMember(request)
            )
        )
}
