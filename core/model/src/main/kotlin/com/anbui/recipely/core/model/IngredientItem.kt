package com.anbui.recipely.core.model

import kotlinx.serialization.Serializable

@Serializable
data class IngredientItem(
    val ingredientId: String,
    val name: String,
    val amount: Float,
    val unit: UnitType,
    val imageUrl: String?,
    val price: Float = 0.23f
)

fun List<IngredientItem>.getTotalPrice(): Float {
    var sum = 0f
    this.forEach {
        sum += it.price * it.amount
    }
    return sum
}

val exampleIngredientItems = listOf(
    IngredientItem(
        ingredientId = "exampleIngredient1",
        name = "Avocado",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        ingredientId = "exampleIngredient2",
        name = "Avocado",
        amount = 2.23f,
        unit = UnitType.Can,
        imageUrl = null
    ),
    IngredientItem(
        ingredientId = "exampleIngredient3",
        name = "NotAvocado",
        amount = 2.4f,
        unit = UnitType.Bag,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        ingredientId = "exampleIngredient4",
        name = "NotAvocadoAgain",
        amount = 2.4f,
        unit = UnitType.Oz,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        ingredientId = "exampleIngredient5",
        name = "NotAvocadoAgainISwear",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        ingredientId = "exampleIngredient6",
        name = "Actually it is an avocado mtfk hahaha haha",
        amount = 2.75f,
        unit = UnitType.Block,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        ingredientId = "exampleIngredient7",
        name = "Avocado",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        ingredientId = "exampleIngredient8",
        name = "Avocado",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    )
)
