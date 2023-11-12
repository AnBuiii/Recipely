package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Query
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.relations.IngredientAccountCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao{
    @Query("SELECT * FROM CART WHERE account_id = :accountId")
    fun getIngredientInCart(accountId: String): Flow<List<AccountWithIngredient>>
}

data class AccountWithIngredient(
    @Embedded
    val cart: IngredientAccountCrossRef,
    @Relation(
        parentColumn = "ingredient_id",
        entityColumn = "_id"
    )
    val ingredient: IngredientEntity
)