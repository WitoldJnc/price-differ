package ru.witoldjnc.pricediffer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PriceDifferApplication

fun main(args: Array<String>) {
	runApplication<PriceDifferApplication>(*args)
}
