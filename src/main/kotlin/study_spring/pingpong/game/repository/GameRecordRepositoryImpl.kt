package study_spring.pingpong.game.repository

import org.springframework.stereotype.Repository
import study_spring.pingpong.game.model.GameRecord
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

@Repository
class GameRecordRepositoryImpl: GameRecordRepository {
    private val records = mutableListOf<GameRecord>()
    private val idGenerator = AtomicLong(1)

    override fun findAll(): List<GameRecord> {
        return records.sortedBy { score -> score.score }
    }

    override fun save(record: GameRecord): GameRecord {
        val newId = idGenerator.getAndIncrement()
        val newRecord = record.copy(id = newId, createdAt = Instant.now())
        records.add(newRecord)
        return newRecord
    }
}
