package com.example.data.room

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.map.HistorySearch
import kotlinx.coroutines.flow.Flow

interface HistorySearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(searchHistory: HistorySearch)

    @Delete
    suspend fun deleteHistory(searchHistory: HistorySearch)

    @Query("SELECT * FROM historySearch")
    fun getFavoriteBooks(): Flow<List<HistorySearch>>

}