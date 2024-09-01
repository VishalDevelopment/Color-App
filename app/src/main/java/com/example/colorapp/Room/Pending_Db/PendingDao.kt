package com.example.colorapp.Room.Pending_Db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.colorapp.Model.PendingFormat
import kotlinx.coroutines.flow.Flow

@Dao
interface PendingDao  {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertColor(data: PendingFormat)
    @Query("SELECT * FROM pending_db")
    fun getAllColor(): Flow<List<PendingFormat>>
    @Query("SELECT COUNT(*) FROM pending_db")
    fun getRowCount(): Flow<Int>
    @Query("DELETE FROM pending_db")
    suspend fun deleteAll()
}