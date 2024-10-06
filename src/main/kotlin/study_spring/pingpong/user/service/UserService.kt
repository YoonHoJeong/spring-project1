package study_spring.pingpong.user.service

import org.springframework.stereotype.Service
import study_spring.pingpong.user.model.User
import study_spring.pingpong.user.repository.UserRepository
import java.util.*

@Service
class UserService(private val userRepository: UserRepository) {
    fun getUserByUsername(username: String): Optional<User> {
        return userRepository.findByUsername(username)
    }

    fun create(username: String): User {
        return userRepository.save(User(
            id = 0,
            username,
        ))
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}
