package com.example.myfilms.model.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myfilms.framework.ui.App

@androidx.room.Database(
    entities = [History::class],
    version = 1,
    exportSchema = false //создавать или нет файл с историями версий
)
abstract class Database : RoomDatabase() {
    abstract fun historyDao(): HistoryDao //ссылка на взаимодействие с таблицей

    companion object {
        private const val DB_NAME = "History.db"
        val db: Database by lazy {
            Room.databaseBuilder(
                App.appInstance,
                Database::class.java,
                DB_NAME
            ).build()
        }
    }
}