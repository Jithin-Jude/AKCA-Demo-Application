package com.jithin.akcademoapplication.data.mapper

import com.jithin.akcademoapplication.data.remote.dto.ProductDto
import com.jithin.akcademoapplication.data.remote.dto.RatingDto
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductMapperTest {

    private val mapper = ProductMapper()

    @Test
    fun mapDtoToDomain_mapsCorrectly() {
        val ratingDto = RatingDto(rate = 4.5, count = 100)
        val productDto = ProductDto(
            id = 1,
            title = "Test Product",
            price = 99.99,
            description = "Description",
            category = "electronics",
            image = "url",
            rating = ratingDto
        )

        val product = mapper.mapDtoToDomain(productDto)

        assertEquals(productDto.id, product.id)
        assertEquals(productDto.title, product.title)
        assertEquals(productDto.price, product.price, 0.001)
        assertEquals(productDto.description, product.description)
        assertEquals(productDto.category, product.category)
        assertEquals(productDto.image, product.image)
        assertEquals(productDto.rating.rate, product.rating.rate, 0.001)
        assertEquals(productDto.rating.count, product.rating.count)
    }
}
