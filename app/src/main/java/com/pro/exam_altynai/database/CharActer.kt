package com.pro.exam_altynai.database

import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity
data class CharActer(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val status: String,
    val species: String,
    val location: String,
    val image: String?,
    val type: String?,
    val gender: String,
    val url: String,
    val episode: List<String>,
    val created: String
)

