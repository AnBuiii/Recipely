package com.anbui.recipely.domain.models

sealed class NotificationType(val type: Int) {
    data object RecipeRecommendation : NotificationType(0)
    data object LikedRecipe : NotificationType(1)
    data object CommentedOnRecipe : NotificationType(2)
    data object FollowedUser : NotificationType(3)
    data object Promo : NotificationType(4)
    data object Order : NotificationType(5)
}
