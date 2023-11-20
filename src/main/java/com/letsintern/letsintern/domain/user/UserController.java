package com.letsintern.letsintern.domain.user;

import com.letsintern.letsintern.domain.user.dto.request.TokenRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignInRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserSignUpRequestDTO;
import com.letsintern.letsintern.domain.user.dto.request.UserUpdateRequestDTO;
import com.letsintern.letsintern.domain.user.dto.response.TokenResponse;
import com.letsintern.letsintern.domain.user.dto.response.UserIdResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.UserInfoResponseDTO;
import com.letsintern.letsintern.domain.user.dto.response.AdminUserListResponseDTO;
import com.letsintern.letsintern.domain.user.service.UserService;
import com.letsintern.letsintern.global.config.user.PrincipalDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@Tag(name = "User")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    public UserIdResponseDTO signUp(@RequestBody @Valid UserSignUpRequestDTO signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @Operation(summary = "로그인")
    @PostMapping("/signin")
    public TokenResponse signIn(@RequestBody @Valid UserSignInRequestDTO signInRequest) {
        return userService.signIn(signInRequest);
    }

    @Operation(summary = "로그아웃")
    @GetMapping("/signout")
    public void signOut(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.signOut(principalDetails);
    }

    @Operation(summary = "토큰 재발급")
    @PostMapping("/reissue")
    public TokenResponse reissueToken(@RequestBody TokenRequestDTO tokenRequestDTO) {
        return userService.reissueToken(tokenRequestDTO);
    }

    @Operation(summary = "회원 탈퇴")
    @GetMapping("/withdraw")
    public void withdraw(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        userService.withdraw(principalDetails);
    }

    @Operation(summary = "마이페이지 사용자 정보")
    @GetMapping("")
    public UserInfoResponseDTO getUserInfo(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return userService.getUserInfo(principalDetails);
    }

    @Operation(summary = "마이페이지 사용자 정보 수정")
    @PatchMapping("")
    public UserIdResponseDTO updateUserInfo(
            @RequestBody UserUpdateRequestDTO userUpdateRequestDTO,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return userService.updateUserInfo(userUpdateRequestDTO, principalDetails);
    }

    @Operation(summary = "어드민 사용자 전체 목록")
    @GetMapping("/admin")
    public AdminUserListResponseDTO getAdminUserTotalList(@PageableDefault(size = 20) Pageable pageable) {
        return userService.getAdminUserTotalList(pageable);
    }

    @Operation(summary = "어드민 사용자 검색 (name, email, phoneNum)")
    @GetMapping("/admin/search")
    public AdminUserListResponseDTO getAdminUserList(@RequestParam String type, @RequestParam String keyword) {
        return userService.getAdminUserList(type, keyword);
    }
}
