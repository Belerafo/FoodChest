package com.example.foodchest.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.foodchest.data.database.entites.FavoritesEntity
import com.example.foodchest.data.database.entites.RecipesEntity

@Database(
    entities = [RecipesEntity::class, FavoritesEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase: RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

}