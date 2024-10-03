package study_spring.pingpong.model.game

import java.time.Instant

data class GameRecord(
    val id: Long,
    val score: Int,
    val createdAt: Instant = Instant.now()
)
