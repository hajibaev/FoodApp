package com.example.data.cloud.models

import com.example.common_api.Mapper
import com.example.data.data.models.DishesData

interface DishesCloudDataMapper {

    fun map(): Mapper<DishesCloud, DishesData>
}

class DishesCloudDataMapperImpl : DishesCloudDataMapper {

    override fun map() = object : Mapper<DishesCloud, DishesData> {
        override fun map(from: DishesCloud): DishesData = createFoodsData(from)
    }

    private fun createFoodsData(foodCloud: DishesCloud) = foodCloud.run {
        DishesData(
            dishes_id = dishes_id,
            dishes_name = dishes_name,
            price = price,
            weight = weight,
            description = description,
            image_url = image_url,
            tegs = tegs
        )
    }

}