package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.relations.IngredientWithAmount
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("select * from Recipe")
    fun getAllRecipes(): Flow<List<RecipeEntity>>

    @Transaction
    @Query("Select * from Recipe  where _id = :recipeId")
    suspend fun getIngredientOfRecipe(recipeId: String): List<RecipeWithIngredient>


//    @Transaction
    @Query(
        "SELECT * from recipe r  " +
                "INNER JOIN contain ON contain.recipe_id = r._id " +
                "INNER JOIN ingredient i ON i._id = contain.ingredient_id"
    )
    suspend fun findRecipeWithIngredient(): Map<RecipeEntity, List<IngredientWithAmount>>


}



