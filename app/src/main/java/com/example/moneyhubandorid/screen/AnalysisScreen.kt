package com.example.moneyhubandorid.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.Screen

@Composable
fun AnalysisScreen(navController: NavHostController) {
    val contextForToast = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Analysis Screen"
        )
        Button(onClick =  {
            Toast.makeText(contextForToast,"This is Analysis Screen.", Toast.LENGTH_SHORT)
                .show()
        }){
            Text("Click")
        }
    }
}