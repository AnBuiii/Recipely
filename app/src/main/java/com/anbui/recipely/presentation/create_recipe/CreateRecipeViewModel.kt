package com.anbui.recipely.presentation.create_recipe

import android.net.Uri
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.anbui.recipely.presentation.create_recipe.components.swap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreateRecipeViewModel @Inject constructor() : ViewModel() {
    private val _coverImages = mutableStateListOf<Uri?>()
    val images: List<Uri?> = _coverImages

    private val _title = mutableStateOf("")
    val title: State<String> = _title

    fun onEvent(event: CreateRecipeEvent) {
        when (event) {
            is CreateRecipeEvent.EnterTitle -> {
                _title.value = event.value
            }

            is CreateRecipeEvent.AddImage -> {
                _coverImages.add(event.value)
            }

            is CreateRecipeEvent.EditImage -> {
                _coverImages[event.index] = event.value

            }
            is CreateRecipeEvent.RemoveImage -> {
                _coverImages.removeAt(event.index)
            }



            CreateRecipeEvent.Swap -> {
                _coverImages.swap(1, 2)
            }


        }
    }


}