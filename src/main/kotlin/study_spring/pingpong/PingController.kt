package study_spring.pingpong

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping

@RestController
class PingController(private val pingConfig: PingConfigProperties) {
    @GetMapping("/ping")
    fun ping(): String {
        return pingConfig.value
    }
}
