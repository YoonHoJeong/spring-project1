package study_spring.pingpong.controller

import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study_spring.pingpong.model.game.GameRecord
import study_spring.pingpong.service.game.GameService
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

@RestController
@RequestMapping("/game")
//@Validated
class GameController(private val gameService: GameService) {
    @GetMapping("/leaderboard")
    fun getLeaderBoard(): ResponseEntity<GetLeaderBoardResponseDto> {
        val avg = gameService.getAvgScore()
        val maxScoreRecord = gameService.getMaxScoreRecord()

        return ResponseEntity.ok(
            GetLeaderBoardResponseDto(
                avg,
                maxScoreRecord
            )
        )
    }

    @PostMapping("/record")
    fun createRecord(@Valid @RequestBody requestDto: CreateRecordRequestDto): ResponseEntity<GameRecord> {
        val newRecord = gameService.createGameRecord(requestDto)
        return ResponseEntity.ok(newRecord)
    }
}

data class GetLeaderBoardResponseDto(
    val avg: Double,
    val maxScoreRecord: GameRecord?
)

data class CreateRecordRequestDto(
    @field:NotNull(message = "Score is required")
    @field:Min(value = 0, message = "Score must be at least 0")
    val score: Int
)
