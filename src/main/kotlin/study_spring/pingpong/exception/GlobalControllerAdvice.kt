package study_spring.pingpong.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    data class ErrorResponse(
        val status: Int,
        val message: String,
    )

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleValidationExceptions(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = ex.message ?: "Validation failed",
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}
