package com.example.foodchest.data.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodchest.models.FoodRecipe
import com.example.foodchest.util.Constants.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity (
    var foodRecipe: FoodRecipe
) {

    @PrimaryKey(autoGenerate = false)
    var id: Int = 0

}