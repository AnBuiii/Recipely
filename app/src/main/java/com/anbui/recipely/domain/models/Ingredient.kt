package com.anbui.recipely.domain.models

data class Ingredient(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val unit: UnitType,
    val calories: Float,
    val carb: Float,
    val protein: Float,
    val fat: Float,
    val price: Float = 0.23f
)

val exampleIngredients = listOf(
    Ingredient(
        id = "exampleIngredient1",
        name = "Almond rice",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient2",
        name = "Asian noodles with tofu",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1601454194-amc-rid2811-image2864.jpeg.jpg?itok=DMbeOv-A"
    ),
    Ingredient(
        id = "exampleIngredient3",
        name = "Asian pasta pot with shrimp",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient4",
        name = "Asian rice noodles",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient5",
        name = "Almond rice",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient6",
        name = "Asian vegetables with tofu",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient7",
        name = "Bacon dumplings with mushroom ragout",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient8",
        name = "Bami Goreng",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient9",
        name = "Baked potatoes",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient10",
        name = "Basic recipe gluten-free noodles",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),
    Ingredient(
        id = "exampleIngredient11",
        name = "Basic recipe noodles",
        calories = 2f,
        carb = 1f,
        protein = 4f,
        fat = 5f,
        unit = UnitType.Unit,
        imageUrl = "https://www.cookingwithamc.info/sites/default/files/styles/large/public/migrate/recipe/1675167788-amc-rid194-image18951.jpeg.jpg?itok=cdUxilNZ"
    ),


)
