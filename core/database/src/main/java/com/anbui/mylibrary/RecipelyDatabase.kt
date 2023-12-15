package com.anbui.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.anbui.database.dao.AccountDao
import com.anbui.database.dao.NotificationDao
import com.anbui.database.dao.OrderDao
import com.anbui.database.dao.RecipeDao
import com.anbui.database.entities.AccountEntity
import com.anbui.database.entities.IngredientEntity
import com.anbui.database.entities.LikeEntity
import com.anbui.database.entities.NotificationEntity
import com.anbui.database.entities.OrderEntity
import com.anbui.database.entities.OrderStatusEntity
import com.anbui.database.entities.RecentEntity
import com.anbui.database.entities.RecipeEntity
import com.anbui.database.entities.StepEntity
import com.anbui.mylibrary.converter.LocalDateTimeConverter
import com.anbui.mylibrary.converter.UnitTypeConverter
import com.anbui.database.entities.relations.IngredientAccountCrossRef
import com.anbui.database.entities.relations.OrderIngredientCrossRef
import com.anbui.database.entities.relations.RecipeIngredientCrossRef

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
