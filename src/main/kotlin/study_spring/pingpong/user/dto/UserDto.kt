package study_spring.pingpong.user.dto

import study_spring.pingpong.user.model.User

data class UserDto(
    val id: Long?,
    val username: String,
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id,
                username = user.username
            )
        }
    }
}
