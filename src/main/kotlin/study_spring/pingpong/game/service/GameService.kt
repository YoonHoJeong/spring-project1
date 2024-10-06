package study_spring.pingpong.game.service

import org.springframework.stereotype.Service
import study_spring.pingpong.game.model.GameRecord
import study_spring.pingpong.game.repository.GameRecordRepository
import java.time.Instant

@Service
class GameService(private val gameRecordRepository: GameRecordRepository) {
     fun getAvgScore(): Double {
        val records = gameRecordRepository.findAll()
        return if (records.isEmpty()) {
            0.0 // 빈 컬렉션일 경우 0.0 반환
        } else {
            records.map { it.score }.average()
        }
    }
     fun getMaxScoreRecord(): GameRecord? {
        return gameRecordRepository.findAll()
                .maxWithOrNull((compareBy<GameRecord> { it.score }
                .thenBy { it.createdAt }))
    }

     fun createGameRecord(score: Int): GameRecord {
        return gameRecordRepository.save(
            GameRecord(
            0,
            score,
            createdAt = Instant.now()
        )
        )
    }
}
