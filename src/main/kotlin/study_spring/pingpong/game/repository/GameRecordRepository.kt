package study_spring.pingpong.game.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import study_spring.pingpong.game.model.GameRecord

interface GameRecordRepository : JpaRepository<GameRecord, Long> {
    override fun findAll(pageable: Pageable): Page<GameRecord>
    fun findByUserId(userId: Long, pageable: Pageable): Page<GameRecord>
}
