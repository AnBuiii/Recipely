package com.anbui.recipely.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.anbui.recipely.data.local.entities.AccountEntity
import com.anbui.recipely.data.local.entities.IngredientEntity
import com.anbui.recipely.data.local.entities.LikeEntity
import com.anbui.recipely.data.local.entities.RecipeEntity
import com.anbui.recipely.data.local.entities.StepEntity

data class RecipeAndOwner(
    @Embedded
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = "owner_id",
        entityColumn = "_id"
    )
    val owner: AccountEntity,
    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id"
    )
    val steps: List<StepEntity>,
    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id"
    )
    val likes: List<LikeEntity>,

    @Relation(
        parentColumn = "_id",
        entityColumn = "recipe_id",
        entity = RecipeIngredientCrossRef::class
    )
    val ingredients: List<IngredientAndCrossRef>
)

