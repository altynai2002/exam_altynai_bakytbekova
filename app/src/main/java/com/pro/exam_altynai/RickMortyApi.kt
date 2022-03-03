package com.pro.exam_altynai

import com.pro.exam_altynai.database.Item
import com.pro.exam_altynai.database.Items
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface RickMortyApi {

    @GET("/api/character/")
    fun getAllChar(): Observable<Items>

    @GET("/api/character/{id}")
    fun getCharacter(@Path("id") id: Long): Single<Item>
}