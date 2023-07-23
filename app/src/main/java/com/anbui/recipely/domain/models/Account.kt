package com.anbui.recipely.domain.models

import com.anbui.recipely.R

data class Account(
    val id: String,
    val name: String,
    val description: String,
    val avatarUrl: String
)

val exampleAccounts = listOf(
    Account(
        id = "exampleAccount1",
        name = "An Bùi",
        description = "Android native developer",
        avatarUrl = "https://scontent.fdad1-1.fna.fbcdn.net/v/t39.30808-6/341759537_537638575202652_5607571306534566825_n.jpg?_nc_cat=109&ccb=1-7&_nc_sid=09cbfe&_nc_ohc=8Az4LciHuIgAX-fc5ol&_nc_ht=scontent.fdad1-1.fna&oh=00_AfC4rMm9DfZiMt3Fj412BqFtmyylN01xZayRKe88es6s-A&oe=64C25F71"
    )
)
