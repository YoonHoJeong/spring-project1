package study_spring.pingpong.game.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import study_spring.pingpong.common.dto.PageableRequest
import study_spring.pingpong.game.model.GameRecord
import study_spring.pingpong.game.repository.GameRecordRepository
import study_spring.pingpong.user.repository.UserRepository
import java.time.Instant

@Service
class GameService(
    private val gameRecordRepository: GameRecordRepository,
    private val userRepository: UserRepository,
) {
    fun findAll(pageable: PageableRequest): Page<GameRecord> {
        return gameRecordRepository.findAll(pageable.toPageable())
    }

    fun findByUserId(userId: Long, pageable: PageableRequest): Page<GameRecord> {
        return gameRecordRepository.findByUserId(userId, pageable.toPageable())
    }

    fun getAvgScore(): Double {
        return gameRecordRepository.getAverageScore()
    }

    fun getMaxScoreRecord(): GameRecord? {
        return gameRecordRepository.findTopByOrderByScoreDescCreatedAtAsc(PageRequest.of(0, 10)).firstOrNull()
    }

    fun createGameRecord(score: Int, userId: Long): GameRecord {
        val user = userRepository.findById(userId)
        if (user.isEmpty) {
            throw IllegalArgumentException() // fixme
        }
        return gameRecordRepository.save(
            GameRecord(
                0,
                score,
                createdAt = Instant.now(),
                user.get(),
            )
        )
    }
}
