package com.example.data.model.map

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
@Entity(tableName = "historySearch")
data class HistorySearch(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val description: String
)