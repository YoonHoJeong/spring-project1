package study_spring.pingpong.user.service

import org.springframework.stereotype.Service
import study_spring.pingpong.user.model.User
import study_spring.pingpong.user.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {
    fun findByUserName(username: String): User? {
        return userRepository.findByUsername(username)
    }

    fun create(username: String): User {
        return userRepository.save(User(
            id = null,
            username,
        ))
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }
}
