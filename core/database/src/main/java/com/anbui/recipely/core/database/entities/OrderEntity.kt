package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "Order")
data class OrderEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("_id")
    val id: String,
    @ColumnInfo("account_id") val accountId: String,
    @ColumnInfo("delivery_info") val deliveryInfo: String,
    @ColumnInfo("time") val time: LocalDateTime,
)

