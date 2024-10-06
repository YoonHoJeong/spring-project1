package study_spring.pingpong.game.repository

import org.springframework.data.jpa.repository.JpaRepository
import study_spring.pingpong.game.model.GameRecord

interface GameRecordRepository: JpaRepository<GameRecord, Long> {

}
