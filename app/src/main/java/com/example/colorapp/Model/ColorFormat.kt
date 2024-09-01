package com.example.colorapp.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "color_db")
data class ColorFormat(
    val colorCode: String="",
    val date: String="",
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
