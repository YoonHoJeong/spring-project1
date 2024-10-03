package study_spring.pingpong

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping


@RestController
class PingController {
    @Value("\${spring.pong.value:init_1}")
    var rtnValue: String = "init_0"

    @GetMapping("/ping")
    fun ping(): String {
        return rtnValue
    }

}
