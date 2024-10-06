package study_spring.pingpong.game.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import study_spring.pingpong.game.model.GameRecord
import study_spring.pingpong.game.service.GameService
import study_spring.pingpong.user.model.UserDto
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/games")
class GameController(private val gameService: GameService) {
    data class CreateRecordRequestBody(
        @field:Min(value = 0, message = "Score must be at least 0")
        @field:NotNull
        val score: Int,

        @field:NotNull
        val userId: Long
    )

    data class GetLeaderBoardResponseDto(
        val avg: Double,
        val maxScoreRecord: GameRecordDto?
    )

    data class GameRecordDto(
        val score: Int,
        val id: Long,
        val user: UserDto,
    ) {
        companion object {
            fun from(gameRecord: GameRecord):GameRecordDto {
                return GameRecordDto(
                    gameRecord.score,
                    gameRecord.id,
                    UserDto.from(gameRecord.user)
                )
            }
        }
    }

    @GetMapping("/all")
    fun getAllRecords(): List<GameRecordDto> {
        return gameService.findAll().map { GameRecordDto.from(it) }
    }

    @GetMapping("/leaderboard")
    fun getLeaderBoard(): GetLeaderBoardResponseDto {
        val avg = gameService.getAvgScore()
        val maxScoreRecord = gameService.getMaxScoreRecord()

        return GetLeaderBoardResponseDto(
            avg,
            maxScoreRecord = maxScoreRecord?.let { GameRecordDto.from(it) }
        )
    }

    @PostMapping("/record")
    @ResponseStatus(HttpStatus.CREATED)
    fun createRecord(@Valid @RequestBody request: CreateRecordRequestBody): GameRecordDto {
        val (score, userId) = request
        val newRecord = gameService.createGameRecord(score, userId)
        return GameRecordDto(
            id = newRecord.id,
            score = newRecord.score,
            user = UserDto.from(newRecord.user),
        )
    }
}

