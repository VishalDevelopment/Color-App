package com.example.colorapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.Model.PendingFormat
import com.example.colorapp.Repo.RepoImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorVm @Inject constructor(private val repoImpl: RepoImpl):ViewModel() {

    fun insertColor (colorFormat: PendingFormat){
        viewModelScope.launch {
            repoImpl.InsertDataIntoPending(colorFormat)
        }
    }
    fun getAllColor() : kotlinx.coroutines.flow.Flow<List<ColorFormat>> = repoImpl.GetDataFromColor()

    fun getAllPending():Flow<List<PendingFormat>> = repoImpl.GetDataFromPending()
    fun getCount():kotlinx.coroutines.flow.Flow<Int> = repoImpl.GetCountRowFromPending()
     fun DeleteAllPending() {
         viewModelScope.launch {
             repoImpl.deleteAllPending()
         }
     }
    fun SendPendingtoFireStore(data :PendingFormat){
        viewModelScope.launch{
            repoImpl.SendDataToCloud(data) }
    }

    fun GetDatatoCloud(){
        viewModelScope.launch{ repoImpl.GetDataToCloud() }
    }

}