package ru.witoldjnc.pricediffer.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.witoldjnc.pricediffer.model.ErrorAddressProductList

@Repository
interface ErrorProductsRepository : MongoRepository<ErrorAddressProductList, String> {
}