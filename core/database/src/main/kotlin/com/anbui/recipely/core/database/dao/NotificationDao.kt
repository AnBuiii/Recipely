package com.anbui.recipely.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anbui.recipely.core.database.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM Notification WHERE userId = :userId")
    fun getUserNotification(userId: String): Flow<List<NotificationEntity>>

    @Insert
    suspend fun insertNotification(notificationEntity: NotificationEntity)

    @Query("Update Notification set isRead = true where _id = :notificationId")
    suspend fun readNotification(notificationId: String)
}