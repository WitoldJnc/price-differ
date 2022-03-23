package ru.witoldjnc.pricediffer.configuration

import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.web.context.annotation.RequestScope
import java.util.concurrent.TimeUnit
import java.util.logging.Level

@Configuration
class SeleniumConfig {
    @Value("\${selenium.driver.name}")
    private lateinit var driver: String

    @Bean
    @RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    fun webDriver(): WebDriver? {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/${driver}")

        val options = ChromeOptions()
                .addArguments("--no-sandbox --disable-dev-shm-usage")
                .setHeadless(true)
        val driver = ChromeDriver(options)
        driver.setLogLevel(Level.WARNING)
        return driver
    }
}