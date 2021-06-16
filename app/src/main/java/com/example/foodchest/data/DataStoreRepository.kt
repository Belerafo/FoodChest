package com.example.foodchest.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.example.foodchest.util.Constants.Companion.DEFAULT_DIET_TYPE
import com.example.foodchest.util.Constants.Companion.DEFAULT_MEAL_TYPE
import com.example.foodchest.util.Constants.Companion.PREFERENCES_BACK_ONLINE
import com.example.foodchest.util.Constants.Companion.PREFERENCES_DIET_TYPE
import com.example.foodchest.util.Constants.Companion.PREFERENCES_DIET_TYPE_ID
import com.example.foodchest.util.Constants.Companion.PREFERENCES_MEAL_TYPE
import com.example.foodchest.util.Constants.Companion.PREFERENCES_MEAL_TYPE_ID
import com.example.foodchest.util.Constants.Companion.PREFERENCES_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKey {
        val selectedMealType = stringPreferencesKey(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = intPreferencesKey(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = stringPreferencesKey(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = intPreferencesKey(PREFERENCES_DIET_TYPE_ID)
        val backOnline = booleanPreferencesKey(PREFERENCES_BACK_ONLINE)
    }

    private val dataStore: DataStore<Preferences> = context.dataStore

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.selectedMealType] = mealType
            preferences[PreferencesKey.selectedMealTypeId] = mealTypeId
            preferences[PreferencesKey.selectedDietType] = dietType
            preferences[PreferencesKey.selectedDietTypeId] = dietTypeId
        }
    }

    suspend fun saveBackOnline(backOnLine: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKey.backOnline] = backOnLine
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val selectedMealType = preferences[PreferencesKey.selectedMealType] ?: DEFAULT_MEAL_TYPE
            val selectedMealTypeID = preferences[PreferencesKey.selectedMealTypeId] ?: 0
            val selectedDietType = preferences[PreferencesKey.selectedDietType] ?: DEFAULT_DIET_TYPE
            val selectedDietTypeID = preferences[PreferencesKey.selectedDietTypeId] ?: 0
            MealAndDietType(
                selectedMealType,
                selectedMealTypeID,
                selectedDietType,
                selectedDietTypeID
            )

        }

    val readBackOnline: Flow<Boolean> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val backOnline = preferences[PreferencesKey.backOnline] ?: false
            backOnline
        }

    data class MealAndDietType(
        val selectedMealType: String,
        val selectedMealTypeId: Int,
        val selectedDietType: String,
        val selectedDietTypeId: Int,
    )

}