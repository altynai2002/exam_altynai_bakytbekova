package com.pro.exam_altynai.database

import androidx.room.PrimaryKey

data class Items(
    val results: List<Item>
)

data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val status: String,
    val species: String,
    val location: Location,
    val image: String?,
    val type: String?,
    val gender: String,
    val origin: Location,
    val episode: List<String>,
    val url: String,
    val created: String
)

data class Location (
    val name: String,
    val url: String
)
