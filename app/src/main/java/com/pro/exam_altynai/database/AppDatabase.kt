package com.pro.exam_altynai.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CharActer::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}
