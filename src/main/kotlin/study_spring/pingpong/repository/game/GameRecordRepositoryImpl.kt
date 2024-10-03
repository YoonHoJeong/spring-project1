package study_spring.pingpong.repository.game

import org.springframework.stereotype.Repository
import study_spring.pingpong.model.game.GameRecord
import java.time.Instant
import java.util.concurrent.atomic.AtomicLong

@Repository
class GameRecordRepositoryImpl: GameRecordRepository {
    private val records = mutableListOf<GameRecord>()
    private val idGenerator = AtomicLong(1)

    override fun findById(id: Long): GameRecord? {
        return records.find { s -> s.id == id }
    }

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
