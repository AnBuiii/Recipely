package com.anbui.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.model.OrderStatus
import java.time.LocalDateTime

@Entity(tableName = "OrderStatus")
data class OrderStatusEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("_id")
    val id: String,
    @ColumnInfo("step") val step: String,
    @ColumnInfo("time") val time: LocalDateTime,
    @ColumnInfo("order_id") val orderId: String
) {
    fun toOrderStatus(): com.anbui.model.OrderStatus {
        return com.anbui.model.OrderStatus(
            id = this.id,
            time = this.time.toString(),
            title = this.step,
        )
    }
}
