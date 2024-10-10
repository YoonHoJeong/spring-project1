package study_spring.pingpong.common.dto

import org.springframework.data.domain.Page

data class PageResponse<T>(
    val content: List<T>,
    val nextPage: Int?,
) {
    companion object {
        fun <T> fromPage(page: Page<T>): PageResponse<T> {
            return PageResponse(
                content = page.content,
                nextPage = if (page.hasNext()) page.number + 1 else null
            )
        }
    }
}