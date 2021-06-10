package com.example.foodchest.data.database.entites

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foodchest.models.Result
import com.example.foodchest.util.Constants.Companion.FAVORITE_RECIPES_TABLE

@Entity(tableName = FAVORITE_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)