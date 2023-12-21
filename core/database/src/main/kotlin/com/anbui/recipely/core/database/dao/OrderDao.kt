package com.anbui.recipely.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.anbui.recipely.core.database.entities.OrderStatusEntity
import com.anbui.recipely.core.database.entities.OrderEntity
import com.anbui.recipely.core.database.relations.AccountWithIngredient
import com.anbui.recipely.core.database.entities.IngredientAccountCrossRef
import com.anbui.recipely.core.database.entities.OrderIngredientCrossRef
import com.anbui.recipely.core.database.relations.OrderWithDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Transaction
    @Query("SELECT * FROM CART WHERE account_id = :accountId")
    fun getIngredientInCart(accountId: String): Flow<List<AccountWithIngredient>>

    @Query("SELECT * FROM CART WHERE account_id = :accountId")
    fun getInCart(accountId: String): Flow<List<IngredientAccountCrossRef>>

    @Query("UPDATE CART SET AMOUNT = :amount WHERE ingredient_id = :ingredientId AND account_id = :accountId")
    suspend fun updateAmountInCart(ingredientId: String, accountId: String, amount: Int)

    @Upsert
    suspend fun insertToCart(ingredientCrossRef: IngredientAccountCrossRef)

    @Transaction
    @Query("SELECT * FROM CART WHERE account_id = :accountId AND ingredient_id = :ingredientId")
    suspend fun getIngredientInCartByIdAndAccountId(
        ingredientId: String,
        accountId: String
    ): List<AccountWithIngredient>

    @Query("DELETE FROM CART WHERE account_id = :accountId AND ingredient_id = :ingredientId")
    suspend fun deleteFromCart(ingredientId: String, accountId: String)

    @Transaction
    @Query("SELECT * FROM `ORDER` WHERE account_id = :accountId")
    fun getAllOrder(accountId: String): Flow<List<OrderWithDetail>>

    @Transaction
    @Query("SELECT * FROM `Order` WHERE _id = :orderId")
    fun getOrderById(orderId: String): Flow<OrderWithDetail>

    @Insert
    suspend fun insertOrder(orderEntity: OrderEntity)

    @Insert
    suspend fun insertOrderDetails(orderIngredientCrossRefs: List<OrderIngredientCrossRef>)

    @Insert
    suspend fun insertOrderStatus(orderStatusEntity: OrderStatusEntity)

}

