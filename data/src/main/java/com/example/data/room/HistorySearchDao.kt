package com.example.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.map.HistorySearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistorySearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(searchHistory: HistorySearchEntity)

    @Delete
    suspend fun deleteHistory(searchHistory: HistorySearchEntity)

    @Query("SELECT * FROM historySearch")
    fun getAllHistorySearch(): Flow<List<HistorySearchEntity>>

}