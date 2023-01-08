package com.adam

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [
    UserEntity::class
],version=1)
abstract class AppDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    companion object {
        private var _database: AppDatabase? = null
        fun build(context: Context?): AppDatabase {
            if(_database == null){
                _database = Room.databaseBuilder(context!!,AppDatabase::class.java,"adam_database").build()
            }
            return _database!!
        }
    }
}