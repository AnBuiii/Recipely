package com.anbui.recipely.di

import android.content.Context
import androidx.room.Room
import com.anbui.recipely.data.local.RecipelyDatabase
import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.dao.NotificationDao
import com.anbui.recipely.data.local.dao.OrderDao
import com.anbui.recipely.data.local.dao.RecipeDao
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
            .databaseBuilder(appContext, RecipelyDatabase::class.java, "Recipely.sqlite")
            .createFromAsset("recipely.sqlite")
            .fallbackToDestructiveMigration() // todo remove
            .build()

    @Singleton
    @Provides
    fun provideAccountDao(db: RecipelyDatabase): AccountDao = db.accountDao

    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipelyDatabase): RecipeDao = db.recipeDao

    @Singleton
    @Provides
    fun provideOrderDao(db: RecipelyDatabase): OrderDao = db.orderDao

    @Singleton
    @Provides
    fun provideNotificationDao(db: RecipelyDatabase): NotificationDao = db.notificationDao
}
