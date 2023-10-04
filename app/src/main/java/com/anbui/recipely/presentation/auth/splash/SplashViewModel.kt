package com.anbui.recipely.presentation.auth.splash

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val recipeRepository: RecipeRepository
) : ViewModel() {



    val accounts = accountRepository.getAllAccount().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
       listOf(),
    )

    init {
        viewModelScope.launch {
            val a = recipeRepository.findIngredientWithRecipeId("kalka")
            Log.d("yesss",a.toString())
        }


    }



}