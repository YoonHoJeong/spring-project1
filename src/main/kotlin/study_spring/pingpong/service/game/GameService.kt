package study_spring.pingpong.service.game

import study_spring.pingpong.controller.CreateRecordRequestDto
import study_spring.pingpong.model.game.GameRecord

interface GameService {
    fun getAvgScore(): Double
    fun getMaxScoreRecord(): GameRecord?
    fun createGameRecord(createRecordRequestDto: CreateRecordRequestDto): GameRecord
}
