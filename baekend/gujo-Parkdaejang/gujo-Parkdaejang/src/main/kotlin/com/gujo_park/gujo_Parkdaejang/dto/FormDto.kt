package com.gujo_park.gujo_Parkdaejang.dto

import com.gujo_park.gujo_Parkdaejang.entity.FormEntity

data class  FormDto (
    val name: String,
    val number: String,
    val content: String
) {
    fun toEntity() : FormEntity {
        return FormEntity(
            name = this.name,
            number = this.number,
            content = this.content
        )
    }
}