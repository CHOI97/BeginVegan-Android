package com.example.data.model.tips

data class MagazineDetailResponse(
    val id: Int,
    val title: String,
    val editor: String,
    val source: String,
    val thumbnail: String,
    val magazineContents: List<MagazineContents>
)

data class MagazineContents(
    val content: String,
    val sequence: Int
)
