package com.anbui.recipely.domain.models

data class Recipe(
    val id: String,
    val title: String,
    val imageUrl: String,
    val description: String,
    val isLike: Boolean,
    val cookTime: String,
    val servings: Int,
    val totalCalories: Float,
    val totalCarb: Float,
    val totalProtein: Float,
    val totalFat: Float,
    val ownerId: String,
    val ownerName: String,
    val ownerAvatarUrl: String,
)

val exampleRecipes = listOf(
    Recipe(
        id = "exampleRecipe1",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam odio libero, iaculis eget lacinia sollicitudin, auctor vitae purus. Suspendisse at semper risus. Nunc id scelerisque purus. Aliquam fringilla ultricies orci eget faucibus.",
        isLike = false,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe2",
        title = "Healthy Taco Salad with fresh vegetable",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "12 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser2",
        ownerName = "Bùi An",
        ownerAvatarUrl = "https://datepsychology.com/wp-content/uploads/2022/09/gigachad.jpg"
    ),
    Recipe(
        id = "exampleRecipe3",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = false,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe4",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe5",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe6",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe7",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe8",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
    Recipe(
        id = "exampleRecipe9",
        title = "Asian white noodle with extra seafood",
        imageUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8fA%3D%3D&w=1000&q=80",
        description = "This Healthy Taco Salad is the universal delight of taco night",
        isLike = true,
        cookTime = "20 Min",
        servings = 4, totalCalories = 120f, totalCarb = 32f, totalProtein = 91f,
        totalFat = 29f,
        ownerId = "exampleUser1",
        ownerName = "An Bùi",
        ownerAvatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=cN7YphncnMoAX958ed4&_nc_ht=scontent.fdad1-1.fna&oh=00_AfA48giUtw1JD4oynAXH0XhZr5hVcuTfgD2RBJqlQdaLbA&oe=64BC70B1"
    ),
)



