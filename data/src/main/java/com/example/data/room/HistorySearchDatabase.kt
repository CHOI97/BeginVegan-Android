package com.example.data.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.data.model.map.HistorySearchEntity

@Database(
    entities = [HistorySearchEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(OrmConverter::class)
abstract class HistorySearchDatabase : RoomDatabase() {
    abstract fun historySearchDao(): HistorySearchDao

    companion object {
        @Volatile
        private var INSTANCE: HistorySearchDatabase? = null

        fun getInstance(context: Context): HistorySearchDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HistorySearchDatabase::class.java,
                    "beginvegan-search-history.db"
                )
                    .fallbackToDestructiveMigration() // Optional: handle migrations more gracefully
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
