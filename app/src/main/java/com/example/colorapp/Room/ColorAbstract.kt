package com.example.colorapp.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.colorapp.Model.ColorFormat

@Database(entities = [ColorFormat::class], version = 1)

abstract class ColorDB():RoomDatabase() {
    abstract fun colorDao():ColorDao
}
