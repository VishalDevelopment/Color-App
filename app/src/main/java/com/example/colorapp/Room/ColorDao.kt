package com.example.colorapp.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.colorapp.Model.ColorFormat
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorDao {
    @Insert()
    fun insertColor(data: ColorFormat)
    @Query("SELECT * FROM color_db")
    fun getAllColor(): Flow<List<ColorFormat>>
    @Query("SELECT COUNT(*) FROM color_db")
    fun getRowCount(): Flow<Int>
}