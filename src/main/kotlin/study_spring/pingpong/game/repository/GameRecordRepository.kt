package study_spring.pingpong.game.repository

import study_spring.pingpong.game.model.GameRecord

interface GameRecordRepository {
    fun findAll(): List<GameRecord>
    fun save(record: GameRecord): GameRecord
}
