package study_spring.pingpong.game.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import study_spring.pingpong.common.dto.PageResponse
import study_spring.pingpong.common.dto.PageableRequest
import study_spring.pingpong.game.dto.GameRecordDto
import study_spring.pingpong.game.service.GameService
import study_spring.pingpong.user.dto.UserDto
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
        val userId: Long,
    )

    data class GetLeaderBoardResponseDto(
        val avg: Double,
        val maxScoreRecord: GameRecordDto?,
    )

    @GetMapping("/all")
    fun getAllRecords(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): PageResponse<GameRecordDto> {
        return gameService.findAll(PageableRequest(page, size)).map { GameRecordDto.from(it) }
            .let { PageResponse.fromPage(it) }
    }

    @GetMapping
    fun getGameRecordsByUserId(
        @RequestParam userId: Long,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int,
    ): PageResponse<GameRecordDto> {
        val records = gameService.findByUserId(userId, PageableRequest(page, size))
        return records.map { GameRecordDto.from(it) }.let { PageResponse.fromPage(it) }
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

