package com.adam

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey
    val email : String,
    @ColumnInfo(name = "fullName")
    val fullName : String,
    @ColumnInfo(name = "access")
    val access : String
){

}