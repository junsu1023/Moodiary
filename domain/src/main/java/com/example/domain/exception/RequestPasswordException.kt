package com.example.domain.exception

sealed class RequestPasswordException(override val message: String): Throwable() {
    class EmptyEmailException(message: String = "이메일을 입력해주세요."): RequestPasswordException(message)
    class InValidEmailException(message: String = "유효한 이메일 형식이 아닙니다."): RequestPasswordException(message)
    class FailedRequestPasswordReset(message: String = "비밀번호 재설정 요청에 실패했습니다."): RequestPasswordException(message)
}