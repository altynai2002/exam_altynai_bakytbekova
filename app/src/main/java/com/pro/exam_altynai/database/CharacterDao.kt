package com.pro.exam_altynai.database

import androidx.room.*
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CharacterDao {

    @Query("SELECT * FROM character")
    fun getAll(): Observable<List<CharActer>>

    @Query("SELECT * FROM character WHERE id = :id")
    fun getById(id: Long?): Single<CharActer>

//    С api в room
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(characterList: List<CharActer>)

}