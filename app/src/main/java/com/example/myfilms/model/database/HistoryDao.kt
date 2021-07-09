package com.example.myfilms.model.database

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History") //запрос к БД
    fun all(): List<History>

    @Query("SELECT * FROM History WHERE movieName LIKE :movieName")
    fun getDataByWord(movieName: String): List<History>

    @Insert(onConflict = OnConflictStrategy.IGNORE) //если поле, которое хотим добавить, уже существует
    fun insert(entity: History)

    @Update
    fun update(entity: History)

    @Delete
    fun delete(entity: History)

    @Query ("DELETE FROM History WHERE movieName = :deleteMovieName")
    fun deleteByCityName(deleteMovieName:String)
}