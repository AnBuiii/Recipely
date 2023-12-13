package com.anbui.recipely.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.dao.NotificationDao
import com.anbui.recipely.data.local.dao.OrderDao
import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.local.entities.AccountEntity
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.NotificationEntity
import com.anbui.recipely.data.local.entities.OrderEntity
import com.anbui.recipely.data.local.entities.OrderStatusEntity
import com.anbui.recipely.data.local.entities.RecentEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.StepEntity
import com.anbui.recipely.data.local.entities.converter.LocalDateTimeConverter
import com.anbui.recipely.data.local.entities.converter.UnitTypeConverter
import com.anbui.recipely.data.local.entities.relations.IngredientAccountCrossRef
import com.anbui.recipely.data.local.entities.relations.OrderIngredientCrossRef
import com.anbui.recipely.data.local.entities.relations.RecipeIngredientCrossRef

@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        AccountEntity::class,
        IngredientEntity::class,
        StepEntity::class,
        LikeEntity::class,
        RecentEntity::class,
        OrderEntity::class,
        OrderStatusEntity::class,
        RecipeIngredientCrossRef::class,
        IngredientAccountCrossRef::class,
        OrderIngredientCrossRef::class,
        NotificationEntity::class
    ],
    exportSchema = false,
)
@TypeConverters(LocalDateTimeConverter::class, UnitTypeConverter::class)
abstract class RecipelyDatabase : RoomDatabase() {
    abstract val accountDao: AccountDao
    abstract val recipeDao: RecipeDao
    abstract val orderDao: OrderDao
    abstract val notificationDao: NotificationDao
}
