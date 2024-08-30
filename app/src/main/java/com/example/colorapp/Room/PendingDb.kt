package com.example.colorapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.Model.PendingFormat

@Database(entities = [PendingFormat::class], version = 1)
abstract class PendingDb : RoomDatabase() {
    abstract fun pendingDao():PendingDao
}