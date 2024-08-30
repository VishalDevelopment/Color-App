package com.example.colorapp.Screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.colorapp.Utilse.ColorVm
import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.Model.PendingFormat
import com.example.colorapp.Utilse.CustomDialog
import com.example.colorapp.Utilse.DesignBox
import com.example.colorapp.ui.theme.PurpleLite
import com.example.colorapp.ui.theme.darkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val viewmodel: ColorVm = hiltViewModel()
    val data = viewmodel.getAllColor().collectAsState(initial = emptyList())
    val count = viewmodel.getCount().collectAsState(initial = 0)
    Log.d("DATA","$data.")
    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value==true){
        CustomDialog(value = "", setShowDialog = {
            showDialog.value = it
        }) {
            color , date ->

            viewmodel.insertColor(PendingFormat(color , date))
            Log.i("CHECK","check : color : $color : date : $date : list :  $data")
        }
    }

    val pendingData = viewmodel.getAllPending().collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                actions = {
                    ExtendedFloatingActionButton(
                        onClick = {
                            if(pendingData!= emptyList<ColorFormat>()) {
                                try {
                                    pendingData.value.forEach {
                                        Log.d("SYNCING", "$it")
                                        viewmodel.SendPendingtoFireStore(
                                            PendingFormat(
                                                it.colorCode,
                                                it.date,
                                                it.id
                                            )
                                        )
                                    }
                                    viewmodel.GetDatatoCloud()
                                    viewmodel.DeleteAllPending()
                                }catch (e:Exception){
                                    Log.d("EXCEPTIONMAIN","${e.message}")
                                }
                            }
                            else{
                                Log.d("SYNCING","Data doesn't exist")

                            }

                        }, shape = RoundedCornerShape(100.dp), containerColor = PurpleLite,
                        elevation = FloatingActionButtonDefaults.elevation(0.dp),
                        modifier = Modifier
                            .wrapContentWidth()
                            .height(50.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = "${count.value}",
                                modifier = Modifier.padding(horizontal = 5.dp),
                                color = Color.White
                            )
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(darkBlue),
                title = { Text(text = "Color App", color = Color.White) }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    // Add Color Operation
                    showDialog.value = true
                },
                shape = RoundedCornerShape(100.dp),
                modifier = Modifier.wrapContentWidth(),
                containerColor = PurpleLite,
                elevation = FloatingActionButtonDefaults.elevation(0.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.wrapContentWidth()
                ) {
                    Text(
                        text = "Add Color",
                        modifier = Modifier.padding(horizontal = 5.dp),
                        color = Color.White
                    )
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            MainScreenData(data.value)
        }
    }
}



@Composable
fun MainScreenData(colorList: List<ColorFormat>) {
    val datalist = listOf<ColorFormat>(
        ColorFormat( "#FFAABB", "29-08-24"),
        ColorFormat( "#FFAABB", "29-08-24"),
        ColorFormat( "#FFAABB", "29-08-24"),
        ColorFormat( "#FFAABB", "29-08-24"),
        ColorFormat( "#FFAABB", "29-08-24")
    )
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {

        items(colorList){
            Box(Modifier.padding(5.dp)){ DesignBox(it) }
        }
    }

}

