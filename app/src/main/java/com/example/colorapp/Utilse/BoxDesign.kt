package com.example.colorapp.Utilse

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.colorapp.Model.ColorFormat
import com.example.colorapp.ui.theme.PurpleLite

@Composable
fun DesignBox(data: ColorFormat) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(Color(android.graphics.Color.parseColor(data.colorCode))),  // Set background color
        modifier = Modifier
            .height(150.dp)
            .width(300.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.5f)
                .padding(5.dp)){
                Text(text = data.colorCode, color = Color.White, fontSize = 22.sp, textAlign = TextAlign.Start)
                Box(modifier = Modifier
                    .fillMaxWidth(.7f)
                    .height(2.dp)
                    .background(Color.White))
            }
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp), horizontalAlignment = Alignment.End){
                Text(text = "Created at", color = Color.White, fontSize = 20.sp, textAlign = TextAlign.End)
                Text(text = data.date, color = Color.White, fontSize = 22.sp, textAlign = TextAlign.End)
            }
        }
    }
}