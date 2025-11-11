package com.example.domain.exception

sealed class LoginException(override val message: String): Throwable() {
    class EmptyEmailException(message: String = "이메일을 입력해주세요."): LoginException(message)
    class InValidEmailException(message: String = "잘못된 이에밀 형식입니다."): LoginException(message)
    class EmptyPasswordException(message: String = "비밀번호를 입력해주세요."): LoginException(message)
    class InValidPasswordException(message: String = "비밀번호가 6자리 이상이어야 합니다."): LoginException(message)
    class FailedLogin(message: String = "로그인에 실패했습니다."): LoginException(message)
}