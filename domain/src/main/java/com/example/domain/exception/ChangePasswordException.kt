package com.example.domain.exception

sealed class ChangePasswordException(override val message: String): Throwable() {
    class EmptyAreaException(message: String = "입력하지 않은 란이 있습니다."): ChangePasswordException(message)
    class InValidPasswordException(message: String = "비밀번호가 6자리 이상이어야 합니다."): ChangePasswordException(message)
    class PasswordsDoNotMatchException(message: String = "비밀번호와 확인이 일치하지 않습니다."): ChangePasswordException(message)
    class FailedChangePassword(message: String = "비밀번호 변경에 실패했습니다."): ChangePasswordException(message)
}