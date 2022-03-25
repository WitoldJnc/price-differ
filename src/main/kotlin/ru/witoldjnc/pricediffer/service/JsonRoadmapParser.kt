package ru.witoldjnc.pricediffer.service

import com.google.gson.Gson
import org.springframework.stereotype.Component
import ru.witoldjnc.pricediffer.dto.RoadmapCategory
import ru.witoldjnc.pricediffer.dto.RoadmapCategoryList
import java.nio.file.Files
import java.nio.file.Paths

//вообще не планируется другие имплементации, поэтому без репозитория
@Component
class JsonRoadmapParser {

    fun getItemsFromRoadmap(): MutableList<RoadmapCategory> {
        val gson = Gson()
        val newBufferedReader = Files.newBufferedReader(Paths.get("src/main/resources/roadmap.json"))
        return gson.fromJson<RoadmapCategoryList>(newBufferedReader, RoadmapCategoryList::class.java).product
    }
}

