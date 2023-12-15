package com.anbui.recipely.di

import android.content.Context
import androidx.room.Room
import com.anbui.database.RecipelyDatabase
import com.anbui.database.AccountDao
import com.anbui.database.NotificationDao
import com.anbui.database.OrderDao
import com.anbui.database.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room
            .databaseBuilder(appContext, com.anbui.database.RecipelyDatabase::class.java, "Recipely.sqlite")
            .createFromAsset("recipely.sqlite")
            .fallbackToDestructiveMigration() // todo remove
            .build()

    @Singleton
    @Provides
    fun provideAccountDao(db: com.anbui.database.RecipelyDatabase): com.anbui.database.AccountDao = db.accountDao

    @Singleton
    @Provides
    fun provideRecipeDao(db: com.anbui.database.RecipelyDatabase): com.anbui.database.RecipeDao = db.recipeDao

    @Singleton
    @Provides
    fun provideOrderDao(db: com.anbui.database.RecipelyDatabase): com.anbui.database.OrderDao = db.orderDao

    @Singleton
    @Provides
    fun provideNotificationDao(db: com.anbui.database.RecipelyDatabase): com.anbui.database.NotificationDao = db.notificationDao
}
