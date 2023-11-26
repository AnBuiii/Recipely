package com.anbui.recipely.presentation.recipe.create_recipe.add_item.add_instruction

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class AddInstructionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    init {
        val id = savedStateHandle.get<String>("instructionId")
        val instruction = savedStateHandle.get<String>("instruction")
        val period = savedStateHandle.get<Float>("period")
        viewModelScope.launch {
            id?.let { id ->
                _uiState.update {
                    it.copy(
                        instruction = instruction ?: "",
                        id = id,
                        period = period.toString()
                    )
                }
            }
        }
    }

    private val _uiState = MutableStateFlow(AddInstructionState())
    val uiState = _uiState.asStateFlow()

    fun onChangeInstruction(newValue: String) {
        _uiState.update {
            it.copy(instruction = newValue)
        }
    }

    fun onChangePeriod(newValue: String) {
        _uiState.update {
            it.copy(period = newValue)
        }
    }

    fun addInstruction() {
        with(_uiState.value) {
            if (instruction.isNotBlank()) {
                try {
                    period.toDouble()
                    _uiState.update {
                        if (it.id == null) {
                            val uuid = UUID.randomUUID().toString()
                            it.copy(success = true, id = uuid)
                        } else {
                            it.copy(success = true)
                        }

                    }
                } catch (_: Exception) {
                    _uiState.update { it.copy(error = "Wrong amount") }
                    _uiState.update { it.copy(error = null) }
                    return
                }
            }
        }
    }
}