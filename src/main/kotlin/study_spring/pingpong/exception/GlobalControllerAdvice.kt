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
        val errors: List<FieldValidationError>?
    )

    data class FieldValidationError(
        val field: String,
        val message: String
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = ex.bindingResult
            .fieldErrors
            .map { error ->
                FieldValidationError(
                    field = error.field,
                    message = error.defaultMessage ?: "Invalid value"
                )
            }

        val errorResponse = ErrorResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            message = "Validation failed",
            errors = errors
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}
