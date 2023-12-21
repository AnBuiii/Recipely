package com.anbui.recipely.core.data.impl

import com.anbui.recipely.core.data.NotificationService
import com.anbui.recipely.core.data.mapper.toNotificationEntity
import com.anbui.recipely.core.data.repository.NotificationRepository
import com.anbui.recipely.core.database.dao.NotificationDao
import com.anbui.recipely.core.datastore.RecipelyPreferencesDataSource
import com.anbui.recipely.core.model.Notification
import com.anbui.recipely.core.model.NotificationType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val notificationDao: NotificationDao,
    private val preferencesDataSource: RecipelyPreferencesDataSource,
    private val notificationService: NotificationService
) : NotificationRepository {
    override fun getCurrentUserNotification(
    ): Flow<List<Notification>> {
        return flow {
            val id = preferencesDataSource.loggedId.first()
            notificationDao.getUserNotification(id).map { map ->
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
            val title = when (notification.notificationType) {
                NotificationType.Like -> "Recipely Like"
                NotificationType.Order -> "Recipely Order"
            }
            notificationService.showNotification(notification.message, title, 2)
        }
    }

    override suspend fun readNotification(notificationId: String) {
        notificationDao.readNotification(notificationId)
    }
}