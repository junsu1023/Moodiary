package com.example.domain.exception

sealed class SignUpException(override val message: String): Throwable() {
    class EmptyEmailException(message: String = "이메일을 입력해주세요."): SignUpException(message)
    class InValidEmailException(message: String = "잘못된 이에밀 형식입니다."): SignUpException(message)
    class EmptyPasswordException(message: String = "비밀번호를 입력해주세요."): SignUpException(message)
    class InValidPasswordException(message: String = "비밀번호가 6자리 이상이어야 합니다."): SignUpException(message)
    class EmptyConfirmPasswordException(message: String = "비밀번호 확인을 입력해주세요."): SignUpException(message)
    class PasswordsDoNotMatchException(message: String = "비밀번호와 확인이 일치하지 않습니다."): SignUpException(message)
    class FailedSignUp(message: String = "회원가입에 실패했습니다."): SignUpException(message)
}