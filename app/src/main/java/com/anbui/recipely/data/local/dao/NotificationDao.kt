package com.anbui.recipely.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.anbui.recipely.data.local.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationDao {
    @Query("SELECT * FROM Notification WHERE userId = :userId")
    fun getUserNotification(userId: String): Flow<List<NotificationEntity>>

    @Insert
    suspend fun insertNotification(notificationEntity: NotificationEntity)
}