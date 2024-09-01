package com.example.colorapp.Room.Color_Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.colorapp.Model.ColorFormat

@Database(entities = [ColorFormat::class], version = 1)

abstract class ColorDB():RoomDatabase() {
    abstract fun colorDao(): ColorDao
}
