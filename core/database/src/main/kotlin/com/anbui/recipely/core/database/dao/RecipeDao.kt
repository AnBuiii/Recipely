package com.anbui.recipely.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.anbui.recipely.core.database.entities.IngredientEntity
import com.anbui.recipely.core.database.entities.LikeEntity
import com.anbui.recipely.core.database.entities.RecentEntity
import com.anbui.recipely.core.database.entities.RecipeEntity
import com.anbui.recipely.core.database.entities.StepEntity
import com.anbui.recipely.core.database.relations.IngredientInRecipe
import com.anbui.recipely.core.database.relations.LikeAndRecipe
import com.anbui.recipely.core.database.relations.RecentAndRecipe
import com.anbui.recipely.core.database.relations.RecipeAndOwner
import com.anbui.recipely.core.database.entities.RecipeIngredientCrossRef
import com.anbui.recipely.core.database.relations.RecipeWithIngredient
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

    @Upsert
    suspend fun addRecent(recentEntity: RecentEntity)

    @Query("SELECT * from Recent WHERE recipe_id = :recipeId AND account_id = :accountId")
    suspend fun getRecentByAccountAndRecipe(accountId: String, recipeId: String): List<RecentEntity>

    @Transaction
    @Query("SELECT * from Recipe WHERE owner_id = :accountId")
    fun getRecipeByAccountId(accountId: String): Flow<List<RecipeAndOwner>>

    @Query("DELETE  FROM Recipe WHERE _id = :recipeId")
    suspend fun deleteRecipe(recipeId: String)

    @Query("SELECT recipe_id FROM `Like` WHERE account_id = :accountId")
    fun getFavouriteRecipeIds(accountId: String): Flow<List<String>>

    @Transaction
    @Query("SELECT * from recipe r  WHERE _id IN (:recipeIds)")
    fun getRecipes(recipeIds: List<String>): Flow<List<RecipeAndOwner>>

    @Transaction
    @Query("SELECT * FROM `LIKE` WHERE account_id = :accountId")
    fun getFavouriteRecipes(accountId: String): Flow<List<LikeAndRecipe>>

    @Transaction
    @Query("SELECT * FROM Recent  WHERE account_id = :accountId")
    fun getAllRecent(accountId: String): Flow<List<RecentAndRecipe>>

    @Transaction
    @Query("SELECT * from recipe r  WHERE title LIKE  '%' || :searchText || '%'")
    suspend fun searchRecipe(searchText: String): List<RecipeAndOwner>

    @Transaction
    @Query("SELECT * from Ingredient r  WHERE name LIKE  '%' || :searchText || '%' LIMIT 4")
    suspend fun searchRecipesByIngredient(searchText: String): IngredientInRecipe?

    @Query("SELECT * from Ingredient r  WHERE name LIKE  '%' || :searchText || '%' LIMIT 4")
    suspend fun searchIngredient(searchText: String): List<IngredientEntity>

    @Query("SELECT * from Ingredient r  WHERE _id = :ingredientId")
    suspend fun getIngredientById(ingredientId: String): IngredientEntity?

    @Transaction
    @Insert
    suspend fun insertContains(recipeIngredientCrossRef: List<RecipeIngredientCrossRef>)

    @Transaction
    @Insert
    suspend fun insertSteps(steps: List<StepEntity>)
}





