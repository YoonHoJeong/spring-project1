package study_spring.pingpong.dto

import study_spring.pingpong.game.model.GameRecord

data class GetLeaderBoardResponseDto(
    val avg: Double,
    val maxScoreRecord: GameRecord?
)
