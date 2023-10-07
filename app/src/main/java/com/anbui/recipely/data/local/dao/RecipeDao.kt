package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Upsert
import com.anbui.recipely.data.local.entities.AccountEntity
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.StepEntity
import com.anbui.recipely.data.local.entities.relations.RecipeAndOwner
import com.anbui.recipely.data.local.entities.relations.RecipeIngredientCrossRef
import com.anbui.recipely.data.local.entities.relations.RecipeWithIngredient
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity)

    @Query("select * from Recipe")
    fun getAllRecipeEntities(): Flow<List<RecipeEntity>>

    @Transaction
    @Query("Select * from Recipe  where _id = :recipeId")
    suspend fun getIngredientOfRecipe(recipeId: String): List<RecipeWithIngredient>

    @Transaction
    @Query("SELECT * from recipe r  ")
    fun getAllRecipes(): Flow<List<RecipeAndOwner>>


    @Transaction
    @Query("SELECT * from recipe r  WHERE _id = :recipeId")
    fun getRecipe(recipeId: String): Flow<RecipeAndOwner>

    @Upsert
    suspend fun insertLike(likeEntity: LikeEntity)

    @Query("DELETE FROM `Like` WHERE recipe_id = :recipeId and account_id = :accountId")
    suspend fun deleteLike(recipeId: String, accountId: String)

}

