package com.anbui.recipely.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anbui.recipely.domain.models.Account
import com.anbui.recipely.domain.models.GenderType
import com.anbui.recipely.domain.models.GenderType.Companion.getType
import java.time.LocalDateTime

@Entity(
    tableName = "Account"
)
data class AccountEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "_id", index = true)
    val id: String,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    val email: String,
    val password: String,
    val bio: String,
    @ColumnInfo(name = "day_of_birth") val dob: LocalDateTime,
    val avatarUrl: String,
    val gender: String,
    val street: String,
    val district: String,
    val province: String,
) {
    fun toAccount(): Account {
        return Account(
            id = id,
            firstName = firstName,
            lastName = lastName,
            email = email,
            password = password,
            bio = bio,
            dob = dob,
            avatarUrl = avatarUrl,
            gender = GenderType.fromType(gender),
            street = street,
            district = district,
            province = province,
        )
    }

    fun getAddress(): String {
        return "$street, $district, $province"
    }
}

fun Account.toAccountEntity(): AccountEntity {

    return AccountEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        password = password,
        bio = bio,
        dob = dob,
        avatarUrl = avatarUrl,
        gender = gender.getType(),
        street = street,
        district = district,
        province = province,
    )
}
