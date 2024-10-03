package study_spring.pingpong.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import study_spring.pingpong.PingConfigProperties

@RestController
class PingController(private val pingConfig: PingConfigProperties) {
    @GetMapping("/ping")
    fun ping(): String {
        return pingConfig.value
    }
}
