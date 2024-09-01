package com.example.colorapp.Room.Pending_Db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.colorapp.Model.PendingFormat

@Database(entities = [PendingFormat::class], version = 1)
abstract class PendingRoomDb : RoomDatabase() {
    abstract fun pendingDao(): PendingDao
}