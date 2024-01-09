package com.anbui.recipely.core.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room
            .databaseBuilder(
                appContext,
                RecipelyDatabase::class.java,
                "Recipely.sqlite"
            )
            .createFromAsset("recipely.sqlite")
//            .fallbackToDestructiveMigration() // todo remove
            .build()
}