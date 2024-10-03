package study_spring.pingpong.service.game

import org.springframework.stereotype.Service
import study_spring.pingpong.controller.CreateRecordRequestDto
import study_spring.pingpong.model.game.GameRecord
import study_spring.pingpong.repository.game.GameRecordRepository
import java.time.Instant

@Service
class GameServiceImpl(private val gameRecordRepository: GameRecordRepository): GameService {
    override fun getAvgScore(): Double {
        val records = gameRecordRepository.findAll()
        return if (records.isEmpty()) {
            0.0 // 빈 컬렉션일 경우 0.0 반환
        } else {
            records.map { it.score }.average()
        }
    }
    override fun getMaxScoreRecord(): GameRecord? {
        return gameRecordRepository.findAll()
                .maxWithOrNull((compareBy<GameRecord> { it.score }
                .thenBy { it.createdAt }))
    }

    override fun createGameRecord(createRecordRequestDto: CreateRecordRequestDto): GameRecord {
        val (score) = createRecordRequestDto
        return gameRecordRepository.save(GameRecord(
            0,
            score,
            createdAt = Instant.now()
        ))
    }
}
