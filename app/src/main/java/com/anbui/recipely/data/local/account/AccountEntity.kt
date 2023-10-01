package com.anbui.recipely.data.local.account

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
data class AccountEntity(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "_id") val id: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val email: String,
    val password: String,
    val bio: String,
    @ColumnInfo(name = "day_of_birth") val dob: String,
    val avatarUrl: String,
    val gender: String,
)