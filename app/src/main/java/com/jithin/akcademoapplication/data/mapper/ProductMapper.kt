package com.jithin.akcademoapplication.data.mapper

import com.jithin.akcademoapplication.data.remote.dto.ProductDto
import com.jithin.akcademoapplication.data.remote.dto.RatingDto
import com.jithin.akcademoapplication.domain.model.Product
import com.jithin.akcademoapplication.domain.model.Rating
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun mapDtoToDomain(dto: ProductDto): Product {
        return Product(
            id = dto.id,
            title = dto.title,
            price = dto.price,
            description = dto.description,
            category = dto.category,
            image = dto.image,
            rating = mapRatingDtoToDomain(dto.rating)
        )
    }

    fun mapRatingDtoToDomain(dto: RatingDto): Rating {
        return Rating(
            rate = dto.rate,
            count = dto.count
        )
    }

    fun mapDtoListToDomainList(dtos: List<ProductDto>): List<Product> {
        return dtos.map { mapDtoToDomain(it) }
    }
}
