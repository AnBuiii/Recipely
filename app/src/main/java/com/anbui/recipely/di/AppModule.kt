package com.anbui.recipely.di

import android.content.Context
import androidx.room.Room
import com.anbui.recipely.data.local.RecipelyDatabase
import com.anbui.recipely.data.local.account.AccountDao
import com.anbui.recipely.util.Constants.DATABASE_NAME
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
            .databaseBuilder(appContext, RecipelyDatabase::class.java, DATABASE_NAME)
            .createFromAsset("recipely.sqlite")
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideAccountDao(db: RecipelyDatabase): AccountDao = db.accountDao
}