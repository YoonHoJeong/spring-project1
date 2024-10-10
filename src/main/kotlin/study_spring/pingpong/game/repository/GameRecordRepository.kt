package study_spring.pingpong.game.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import study_spring.pingpong.game.model.GameRecord

interface GameRecordRepository : JpaRepository<GameRecord, Long> {
    override fun findAll(pageable: Pageable): Page<GameRecord>
    fun findByUserId(userId: Long, pageable: Pageable): Page<GameRecord>

    @Query("select avg(gr.score) from GameRecord gr")
    fun getAverageScore(): Double

    @Query("SELECT gr FROM GameRecord gr ORDER BY gr.score DESC, gr.createdAt ASC")
    fun findTopByOrderByScoreDescCreatedAtAsc(pageable: Pageable): List<GameRecord>
}
