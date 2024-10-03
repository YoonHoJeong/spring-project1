package study_spring.pingpong.repository.game

import study_spring.pingpong.model.game.GameRecord

interface GameRecordRepository {
    fun findById(id: Long): GameRecord?
    fun findAll(): List<GameRecord>
    fun save(record: GameRecord): GameRecord
}
