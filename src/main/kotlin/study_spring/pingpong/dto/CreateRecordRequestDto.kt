package study_spring.pingpong.dto

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class CreateRecordRequestDto(
    @field:NotNull(message = "Score is required")
    @field:Min(value = 0, message = "Score must be at least 0")
    val score: Int
)
