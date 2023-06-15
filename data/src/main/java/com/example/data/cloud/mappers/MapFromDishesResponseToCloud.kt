package com.example.data.cloud.mappers

import com.example.common_api.Mapper
import com.example.data.cloud.models.DishesCloud
import com.example.data.cloud.models.DishesResponseCloud

class MapFromDishesResponseToCloud : Mapper<DishesResponseCloud, DishesCloud> {
    override fun map(from: DishesResponseCloud): DishesCloud {
        if (from.dishes.isEmpty()){
//            return DishesCloud.unknown
        }

        val dishes = from.dishes.first()
        return DishesCloud(
            dishes_id = dishes.dishes_id,
            dishes_name = dishes.dishes_name,
            price = dishes.price,
            weight = dishes.weight,
            description = dishes.description,
            image_url = dishes.image_url,
            tegs = dishes.tegs
        )
    }
}