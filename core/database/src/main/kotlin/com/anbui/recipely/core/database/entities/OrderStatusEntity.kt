package com.anbui.recipely.core.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.core.model.OrderStatus
import kotlinx.datetime.LocalDateTime

@Entity(tableName = "OrderStatus")
data class OrderStatusEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("_id")
    val id: String,
    @ColumnInfo("step") val step: String,
    @ColumnInfo("time") val time: LocalDateTime,
    @ColumnInfo("order_id") val orderId: String
) {
    fun toOrderStatus(): OrderStatus {
        return OrderStatus(
            id = this.id,
            time = this.time.toString(),
            title = this.step,
        )
    }
}
