package study_spring.pingpong.user.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import study_spring.pingpong.user.model.User
import study_spring.pingpong.user.service.UserService
import javax.validation.Valid
import javax.validation.constraints.NotBlank

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    data class CreateUserRequestDto(
        @field:NotBlank(message = "Username must not be empty")
        val username: String
    )

    data class UserResponse(
        val id: Long?,
        val username: String
    ) {
        companion object {
            fun from(user: User): UserResponse {
                return UserResponse(
                    id = user.id,
                    username = user.username
                )
            }
        }
    }

    @GetMapping("/all")
    fun getAllUsers(): List<UserResponse> {
        val users = userService.findAll()
        return users.map { UserResponse.from(it) }
    }

    @GetMapping
    fun getUserByUsername(@RequestParam username: String?): UserResponse {
        if (username.isNullOrEmpty()) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is required")
        }
        val user = userService.findByUserName(username)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with username: $username")
        return UserResponse.from(user)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@Valid @RequestBody request: CreateUserRequestDto): UserResponse {
        val user = userService.create(request.username)
        return UserResponse.from(user)
    }
}
