package ru.witoldjnc.pricediffer.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.witoldjnc.pricediffer.dto.Products

interface ProductRepository : MongoRepository<Products, String> {
}