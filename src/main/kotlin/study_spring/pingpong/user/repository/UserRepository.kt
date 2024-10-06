package study_spring.pingpong.user.repository

import org.springframework.data.jpa.repository.JpaRepository
import study_spring.pingpong.user.model.User
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>
}
