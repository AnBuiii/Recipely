package com.anbui.recipely.presentation.auth.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anbui.recipely.domain.repository.AccountRepository
import com.anbui.recipely.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val recipeRepository: RecipeRepository
) : ViewModel() {

    val accounts = recipeRepository.findIngredientWithRecipeId("kalka").stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        listOf()
    )

//    init {
//        viewModelScope.launch {
//            val a = recipeRepository.findIngredientWithRecipeId("kalka").first()
//            Log.d("yesss",a.toString())
//        }
//
//
//    }
}
