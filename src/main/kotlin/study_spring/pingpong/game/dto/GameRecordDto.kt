package study_spring.pingpong.game.dto

import study_spring.pingpong.game.model.GameRecord
import study_spring.pingpong.user.dto.UserDto

data class GameRecordDto(
    val score: Int,
    val id: Long,
    val user: UserDto,
) {
    companion object {
        fun from(gameRecord: GameRecord): GameRecordDto {
            return GameRecordDto(
                gameRecord.score,
                gameRecord.id,
                UserDto.from(gameRecord.user)
            )
        }
    }
}
