package com.example.project_machimo.inn_jeong.mypage.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequestDto {
    private String uId;

//    @NotBlank(message = "이름을 입력해주세요.")
    private String uName;

    @NotBlank(message = "비밀번호을 입력해주세요.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String uPassword;

    @NotBlank(message = "비밀번호를 한번 더 입력해주세요.")
    private String uPwdCheck;
//    @Pattern(regexp = "(?=.*[0-9]){8}", message = "숫자만 입력 가능합니다.")
//    @NotBlank(message = "생년월일을 입력해주세요.")
    @Positive
    private String uJumin;

    @NotBlank(message = "닉네임을 입력해주세요.")
    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
    private String uNickname;

    @Email(message = "이메일 형식에 맞지 않습니다.ex)abc@naver.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String uEmail;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$",message = "휴대폰 번호를 잘못 입력하셨습니다.")
    private String uPhone;

    @NotBlank(message = "주소를 입력해주세요.")
    private String uAddress;

    private String uAddressSub;
    private String uAddrPostcode;
    private String uSocial;
}
