package com.example.data.cloud.mappers

import com.example.common_api.Mapper
import com.example.data.cloud.models.DishesCloud
import com.example.data.data.models.DishesData

class MapFromDishesCloudToData : Mapper<DishesCloud, DishesData>{
    override fun map(from: DishesCloud) = from.run {
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