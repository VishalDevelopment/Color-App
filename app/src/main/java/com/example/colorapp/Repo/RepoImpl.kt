package com.example.colorapp.Repo

import android.app.Application
import android.util.Log
import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.Model.PendingFormat
import com.example.colorapp.Room.ColorDB
import com.example.colorapp.Room.ColorDao
import com.example.colorapp.Room.PendingDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val colordb: ColorDao,
    private val pendingdb: PendingDao,
    val firebaseFirestore: FirebaseFirestore,
) : Repo {


    override  fun InsertDataintoColor(data: ColorFormat) {
        Log.d("DEBUGDATA", "$data")
        colordb.insertColor(data)
    }

    override fun GetDataFromColor(): Flow<List<ColorFormat>> = colordb.getAllColor()

    override suspend fun InsertDataIntoPending(data: PendingFormat) {
        pendingdb.insertColor(data)
    }

    override fun GetDataFromPending(): Flow<List<PendingFormat>> = pendingdb.getAllColor()
    override fun GetCountRowFromPending(): Flow<Int> = pendingdb.getRowCount()
    override suspend fun deleteAllPending() {
        pendingdb.deleteAll()
    }


    override suspend fun SendDataToCloud(data: PendingFormat) {
        firebaseFirestore.collection("COLORS").document("${data.id}").set(data)
            .addOnSuccessListener {
                Log.d("FIREBASESTORE", "SUCCESS : $data")
            }
            .addOnFailureListener {
                Log.d("FIREBASESTORE", "FAILED")

            }
    }

    override suspend fun GetDataToCloud()  {
        firebaseFirestore.collection("COLORS").get().addOnSuccessListener {
            for (document in it.documents) {
                val data: ColorFormat? = document.toObject(ColorFormat::class.java)
                try {
                    if (data != null) {

                            Log.d("FIRECLOUD", "Data from firebase $data")
                            InsertDataintoColor(data)

                    }
                } catch (e: Exception) {
                    Log.d("EXCEPTIONFOUND", "${e.message}")
                }
            }

        }
    }


}