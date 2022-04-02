package ru.witoldjnc.pricediffer.controller

import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.witoldjnc.pricediffer.model.ErrorAddressProductList
import ru.witoldjnc.pricediffer.repository.ErrorProductsRepository
import java.time.LocalDate

@RestController
@RequestMapping("/api/v1/error")
class ErrorController(
        private val errorProductsRepo: ErrorProductsRepository
) {

    @GetMapping
    fun getAllErrors(): ResponseEntity<MutableList<ErrorAddressProductList>> {
        return ResponseEntity.ok()
                .body(errorProductsRepo.findAll())
    }

    @GetMapping("/id")
    fun getErrorById(@RequestParam id: String): ResponseEntity<ErrorAddressProductList> {
        return ResponseEntity.ok()
                .body(errorProductsRepo.findByIdOrNull(id))
    }

    @GetMapping("/date")
    fun getErrorByDate(@RequestParam date: String): ResponseEntity<ErrorAddressProductList> {
        return ResponseEntity.ok()
                .body(errorProductsRepo.findByDate(LocalDate.parse(date)))
    }

    @DeleteMapping("/id")
    fun deleteErrorById(@RequestParam id: String): ResponseEntity<String> {
        errorProductsRepo.deleteById(id)
        return ResponseEntity.ok()
                .body(id)
    }

}
