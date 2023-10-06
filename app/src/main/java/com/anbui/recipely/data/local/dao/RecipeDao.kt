package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.anbui.recipely.data.local.entities.AccountEntity
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.StepEntity
import com.anbui.recipely.data.local.entities.relations.RecipeIngredientCrossRef
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

//
//    @Transaction
//    @Query(
//        "SELECT * from recipe r  " +
//                "INNER JOIN contain ON contain.recipe_id = r._id " +
//                "INNER JOIN ingredient i ON i._id = contain.ingredient_id " + ""
// //                "INNER JOIN Account ON Account._id == r.owner_id"
//    )
//    fun findRecipeWithIngredient(): Flow<Map<RecipeAndOwner, List<IngredientWithAmount>>>

    @Transaction
    @Query(
        "SELECT * from recipe r  "
    )
    fun findRecipeWithIngredient(): Flow<List<RecipeAndOwner>>
}

data class RecipeAndOwner(
    @Embedded
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = "owner_id",
        entityColumn = "_id"
    )
    val owner: AccountEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id"
    )
    val steps: List<StepEntity>,
    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id"
    )
    val likes: List<LikeEntity>,

    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id",
        entity = RecipeIngredientCrossRef::class
    )
    val ingredients: List<IngredientAndCrossRef>
)

data class IngredientAndCrossRef(
    @Embedded
    val crossRef: RecipeIngredientCrossRef,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "_id"
    )
    val ingredient: IngredientEntity
)
