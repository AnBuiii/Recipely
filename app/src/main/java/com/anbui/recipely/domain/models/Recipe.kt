package com.anbui.recipely.domain.models

data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val isLike: Boolean,
    val cookTime: String,
    val totalCalories: Int,
    val totalCarb: Int,
    val totalProtein: Int,
    val totalFat: Int,
    val ownerId: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
)

val exampleRecipes = listOf(
    Recipe(
        id = "exampleRecipe1",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = false,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe2",
        title = "Healthy Taco Salad with fresh vegetable",
        cookTime = "12 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser2",
        ownerAvatarUrl = "https://datepsychology.com/wp-content/uploads/2022/09/gigachad.jpg",
        ownerName = "Bùi An"
    ),
    Recipe(
        id = "exampleRecipe3",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = false,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe4",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe5",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe6",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe7",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe8",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
    Recipe(
        id = "exampleRecipe9",
        title = "Asian white noodle with extra seafood",
        cookTime = "20 Min",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        totalCarb = 32, totalFat = 29, totalCalories = 120, totalProtein = 91,
        ownerId = "exampleUser1",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1",
        ownerName = "An Bùi"
    ),
)



