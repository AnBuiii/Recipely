package com.anbui.recipely.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anbui.recipely.core.database.dao.NotificationDao
import com.anbui.recipely.core.database.dao.OrderDao
import com.anbui.recipely.core.database.dao.RecipeDao
import com.anbui.recipely.core.database.entities.OrderStatusEntity
import com.anbui.recipely.core.database.converter.LocalDateTimeConverter
import com.anbui.recipely.core.database.converter.UnitTypeConverter
import com.anbui.recipely.core.database.dao.AccountDao
import com.anbui.recipely.core.database.entities.AccountEntity
import com.anbui.recipely.core.database.entities.IngredientEntity
import com.anbui.recipely.core.database.entities.LikeEntity
import com.anbui.recipely.core.database.entities.NotificationEntity
import com.anbui.recipely.core.database.entities.OrderEntity
import com.anbui.recipely.core.database.entities.RecentEntity
import com.anbui.recipely.core.database.entities.RecipeEntity
import com.anbui.recipely.core.database.entities.StepEntity
import com.anbui.recipely.core.database.entities.IngredientAccountCrossRef
import com.anbui.recipely.core.database.entities.OrderIngredientCrossRef
import com.anbui.recipely.core.database.entities.RecipeIngredientCrossRef

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
