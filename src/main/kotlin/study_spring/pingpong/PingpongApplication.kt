package study_spring.pingpong

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

/*
* @SpringBootApplication 어노테이션을 통해 아래 어노테이션이 자동으로 설정된다.
* @Configuration
* @EnableAutoConfiguration
* @ComponentScan
* */
@SpringBootApplication
open class PingpongApplication

fun main(args: Array<String>) {
	runApplication<PingpongApplication>(*args)
}
