package study_spring.pingpong

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class PingpongApplication

fun main(args: Array<String>) {
	runApplication<PingpongApplication>(*args)
}
