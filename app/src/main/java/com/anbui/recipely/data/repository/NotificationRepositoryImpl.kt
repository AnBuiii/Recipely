package com.anbui.recipely.data.repository

import com.anbui.model.Notification
import com.anbui.database.dao.NotificationDao
import com.anbui.recipely.data.mapper.toNotificationEntity
import com.anbui.recipely.domain.repository.CurrentPreferences
import com.anbui.recipely.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao,
    private val currentPreferences: CurrentPreferences
) : NotificationRepository {
    override fun getCurrentUserNotification(
    ): Flow<List<Notification>> {
        return flow {
            val id = currentPreferences.getLoggedId().first()
            notificationDao.getUserNotification(id ?: "").map { map ->
                map.map { notification ->
                    notification.toNotification()
                }
            }.collect {
                emit(it)
            }
        }
    }

    override suspend fun insertNotification(notification: Notification) {
        notification.toNotificationEntity().let {
            notificationDao.insertNotification(it)
        }
    }

    override suspend fun readNotification(notificationId: String) {
        notificationDao.readNotification(notificationId)
    }
}