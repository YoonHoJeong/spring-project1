package study_spring.pingpong.user.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study_spring.pingpong.user.model.User
import study_spring.pingpong.user.service.UserService
import javax.validation.Valid
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {
    data class GetUserByUserNamePathVariable(
        @field:NotNull
        val username: String?
    )

    data class CreateUserRequestDto(
        @field:NotNull
        val username: String?
    )

    @GetMapping
    fun getAllUser(): List<User> {
        return userService.findAll()
    }

    @GetMapping("/{username}")
    fun getUserByUserName(@PathVariable username: String): ResponseEntity<String> {
        // TODO
        return ResponseEntity.ok(username)
    }

    @PostMapping("/create")
    fun createUser(@Valid @RequestBody request: CreateUserRequestDto): User {
        val (username) = request
        if (username.isNullOrBlank()) {
            throw IllegalArgumentException("username is required")
        }
        return userService.create(username)
    }
}
