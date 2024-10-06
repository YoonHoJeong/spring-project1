package study_spring.pingpong.game.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study_spring.pingpong.dto.CreateRecordRequestDto
import study_spring.pingpong.dto.GetLeaderBoardResponseDto
import study_spring.pingpong.game.model.GameRecord
import study_spring.pingpong.game.service.GameService
import javax.validation.Valid

@RestController
@RequestMapping("/game")
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

    @PostMapping("/save")
    fun createRecord(@Valid @RequestBody request: CreateRecordRequestDto): ResponseEntity<GameRecord> {
        val newRecord = gameService.createGameRecord(request.score)
        return ResponseEntity.ok(newRecord)
    }
}

