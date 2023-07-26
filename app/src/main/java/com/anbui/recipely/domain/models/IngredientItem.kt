package com.anbui.recipely.domain.models

data class IngredientItem(
    val id: String,
    val name: String,
    val amount: Float,
    val unit: UnitType,
    val imageUrl: String?,
)

val exampleIngredientItems = listOf(
    IngredientItem(
        id = "exampleIngredient1",
        name = "Avocado",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        id = "exampleIngredient2",
        name = "Avocado",
        amount = 2.23f,
        unit = UnitType.Can,
        imageUrl = null
    ),
    IngredientItem(
        id = "exampleIngredient3",
        name = "NotAvocado",
        amount = 2.4f,
        unit = UnitType.Bag,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        id = "exampleIngredient4",
        name = "NotAvocadoAgain",
        amount = 2.4f,
        unit = UnitType.Oz,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        id = "exampleIngredient5",
        name = "NotAvocadoAgainISwear",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        id = "exampleIngredient6",
        name = "Actually it is an avocado mtfk hahaha haha",
        amount = 2.75f,
        unit = UnitType.Block,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        id = "exampleIngredient7",
        name = "Avocado",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
    IngredientItem(
        id = "exampleIngredient8",
        name = "Avocado",
        amount = 2.4f,
        unit = UnitType.Unit,
        imageUrl = "https://img.taste.com.au/qh8g3dSr/taste/2017/02/avo-120355-1.jpg"
    ),
)
