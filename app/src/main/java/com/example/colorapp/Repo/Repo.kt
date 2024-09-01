package com.example.colorapp.Repo

import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.Model.PendingFormat
import kotlinx.coroutines.flow.Flow

interface Repo {

suspend   fun  InsertDataintoColor(data: List<ColorFormat>)
    fun GetDataFromColor():Flow<List<ColorFormat>>


    suspend fun InsertDataIntoPending(data: PendingFormat)
    fun GetDataFromPending():Flow<List<PendingFormat>>
    fun GetCountRowFromPending():Flow<Int>
    suspend  fun deleteAllPending()

    suspend  fun SendDataToCloud(data: PendingFormat)
    suspend fun GetDataToCloud()

}