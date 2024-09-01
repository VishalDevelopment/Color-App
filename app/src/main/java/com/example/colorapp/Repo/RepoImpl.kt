package com.example.colorapp.Repo

import android.util.Log
import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.Model.PendingFormat
import com.example.colorapp.Room.Color_Db.ColorDao
import com.example.colorapp.Room.Pending_Db.PendingDao
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepoImpl @Inject constructor(
    private val colordb: ColorDao,
    private val pendingdb: PendingDao,
    val firebaseFirestore: FirebaseFirestore,
) : Repo {

//
    override  suspend fun InsertDataintoColor(data: List<ColorFormat>) {
        Log.d("INSERTINGCOLOR", "$data")
    data.forEach{

        colordb.insertColor(it)
    }
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
      val colorList = mutableListOf<ColorFormat>()
        firebaseFirestore.collection("COLORS").get().addOnSuccessListener {
            for (document in it.documents) {
                val data: ColorFormat? = document.toObject(ColorFormat::class.java)
                    if (data != null) {
//                            Log.d("FIRECLOUD", "Data from firebase $data")
                            colorList.add(data)
                    }
            }
            Log.d("FIRECLOUD","$colorList")
            CoroutineScope(Dispatchers.IO).launch {
                InsertDataintoColor(colorList)
            }

        }
        if (colorList.isNotEmpty()){


        }
        else{
            Log.d("FIRECLOUD","empty list")

        }
    }


}