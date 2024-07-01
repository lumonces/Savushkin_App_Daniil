package com.example.savushkin.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [DirectoryEntity::class, RequestEntity::class, ProductOfRequestEntity::class], version = 1)
abstract class MyRoom : RoomDatabase() {

    abstract fun Dao() : MyDAO

    companion object {
        private var INSTANCE : MyRoom? = null

        fun getDataBase(context : Context) : MyRoom {
            synchronized(this) {
                var tempInstance = INSTANCE
                if(tempInstance == null) {
                    tempInstance = Room.databaseBuilder(
                        context.applicationContext,
                        MyRoom::class.java,
                        "Savushkin"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = tempInstance
                }
                return tempInstance
            }
        }
    }
}