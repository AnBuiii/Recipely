package com.anbui.recipely.data.repository

import com.anbui.recipely.data.local.dao.AccountWithIngredient
import com.anbui.recipely.data.local.dao.OrderDao
import com.anbui.recipely.domain.repository.CartRepository
import com.anbui.recipely.domain.repository.CurrentPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val orderDao: OrderDao,
    private val currentPreferences: CurrentPreferences
) : CartRepository {
    override fun getAllInCartOfCurrentAccount(): Flow<List<AccountWithIngredient>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            orderDao.getIngredientInCart(id ?: "").collect{
                emit(it)
            }
        }
    }
}