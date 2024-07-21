package com.example.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.model.map.HistorySearch

@Database(
    entities = [HistorySearch::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(OrmConverter::class)
abstract class HistorySearchDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): HistorySearchDao

    companion object {
        @Volatile
        private var INSTANCE: HistorySearchDatabase? = null
    }

}