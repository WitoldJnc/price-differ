package ru.witoldjnc.pricediffer.service

import com.google.gson.Gson
import org.springframework.stereotype.Component
import ru.witoldjnc.pricediffer.dto.RoadmapListProducts
import ru.witoldjnc.pricediffer.dto.RoadmapProduct
import java.nio.file.Files
import java.nio.file.Paths

//вообще не планируется другие имплементации, поэтому без репозитория
@Component
class JsonRoadmapParser {

    fun parseRoadMap(): List<RoadmapProduct> {
        val gson = Gson()
        val newBufferedReader = Files.newBufferedReader(Paths.get("src/main/resources/roadmap.json"))
        return gson.fromJson<RoadmapListProducts>(newBufferedReader, RoadmapListProducts::class.java).product
    }
}

