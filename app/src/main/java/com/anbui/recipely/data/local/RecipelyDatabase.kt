package com.anbui.recipely.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.local.entities.AccountEntity
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.relations.RecipeIngredientCrossRef


@Database(
    version = 1,
    entities = [
        RecipeEntity::class,
        AccountEntity::class,
        RecipeIngredientCrossRef::class,
        IngredientEntity::class
    ],
    exportSchema = false
)
abstract class RecipelyDatabase : RoomDatabase() {
    abstract val accountDao: AccountDao
    abstract val recipeDao: RecipeDao
}