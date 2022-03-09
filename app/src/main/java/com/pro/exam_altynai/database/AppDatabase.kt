package com.pro.exam_altynai.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pro.exam_altynai.TypeListConverter

@TypeConverters(TypeListConverter::class)
@Database(entities = [CharActer::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
