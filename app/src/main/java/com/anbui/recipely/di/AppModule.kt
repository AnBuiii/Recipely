package com.anbui.recipely.di

import android.content.Context
import androidx.room.Room
import com.anbui.recipely.data.local.RecipelyDatabase
import com.anbui.recipely.data.local.dao.AccountDao
import com.anbui.recipely.data.local.dao.RecipeDao
import com.anbui.recipely.data.repository.AccountRepositoryImpl
import com.anbui.recipely.data.repository.RecipeRepositoryImpl
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext appContext: Context) =
        Room
//            .databaseBuilder(appContext, RecipelyDatabase::class.java, DATABASE_NAME)
            .databaseBuilder(appContext, RecipelyDatabase::class.java, "Recipely.sqlite")
            .createFromAsset("recipely.sqlite")
//            .createFromFile(File("/data/user/0/com.anbui.recipely/databases/Recipely.sqlite"))
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideAccountDao(db: RecipelyDatabase): AccountDao = db.accountDao

    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipelyDatabase): RecipeDao = db.recipeDao

    @Singleton
    @Provides
    fun provideAccountRepository(
        accountDao: AccountDao,
        @ApplicationContext appContext: Context
    ): AccountRepository {
        return AccountRepositoryImpl(accountDao, appContext)
    }

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeDao: RecipeDao,
        @ApplicationContext appContext: Context
    ): RecipeRepository {
        return RecipeRepositoryImpl(recipeDao, appContext)
    }
}
