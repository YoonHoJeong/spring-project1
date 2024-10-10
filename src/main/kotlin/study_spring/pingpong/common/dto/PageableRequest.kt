package study_spring.pingpong.common.dto

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

data class PageableRequest(val page: Int, val size: Int) {
    fun toPageable(): Pageable {
        return PageRequest.of(page, size)
    }
}