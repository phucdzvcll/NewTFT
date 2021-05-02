package com.free.data.map

import com.free.common_jvm.extension.defaultEmpty
import com.free.common_jvm.extension.defaultZero
import com.free.common_jvm.mapper.Mapper
import com.free.data.entities.RecipeResponse
import com.free.domain.entities.RecipeEntity

class RecipeMapper : Mapper<RecipeResponse?, RecipeEntity>() {
    override fun map(input: RecipeResponse?): RecipeEntity {
        val name = input?.name.defaultEmpty().replace(" ", "").replace("'", "").replace(".", "")
        return RecipeEntity(
            name = input?.name.defaultEmpty(),
            id = input?.id.defaultZero(),
            imgUrl = "https://rerollcdn.com/items/$name.png"
        )
    }
}