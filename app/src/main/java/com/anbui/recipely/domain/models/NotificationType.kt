package com.anbui.recipely.domain.models

sealed class NotificationType(val type: Int) {
    object RecipeRecommendation : NotificationType(0)
    object LikedRecipe : NotificationType(1)
    object CommentedOnRecipe : NotificationType(2)
    object FollowedUser : NotificationType(3)
    object Promo : NotificationType(4)
    object Order : NotificationType(5)
}
