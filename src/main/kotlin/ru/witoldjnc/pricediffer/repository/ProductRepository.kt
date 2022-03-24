package ru.witoldjnc.pricediffer.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.witoldjnc.pricediffer.model.Products
import java.time.LocalDate

@Repository
interface ProductRepository : MongoRepository<Products, String> {
    fun findFirstByOrderByDateDesc(): Products
    fun findByDate(date: LocalDate?): Products
}