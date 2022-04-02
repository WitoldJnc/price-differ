package ru.witoldjnc.pricediffer.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.witoldjnc.pricediffer.model.Products
import ru.witoldjnc.pricediffer.repository.ProductRepository
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/product")
class ProductController(
        private val productRepo: ProductRepository
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<MutableList<Products>> {
        return ResponseEntity.ok()
                .body(productRepo.findAll())
    }

    @GetMapping("/last")
    fun getLastRecord(): ResponseEntity<Products> {
        return ResponseEntity.ok()
                .body(productRepo.findFirstByOrderByDateDesc())
    }

    @GetMapping("/date")
    fun getByDate(@RequestParam date:String): ResponseEntity<Products> {
        return ResponseEntity.ok()
                .body(productRepo.findByDate(LocalDate.parse(date)))
    }
}