package com.anbui.recipely.domain.repository

import com.anbui.recipely.data.local.dao.AccountWithIngredient
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    fun getAllInCartOfCurrentAccount(): Flow<List<AccountWithIngredient>>
}