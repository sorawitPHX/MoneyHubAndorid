package com.example.moneyhubandorid.screen.Analysis

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.Screen
import com.example.moneyhubandorid.screen.Finance.Summary

@Composable
fun AnalysisScreen(navController: NavHostController) {
    val contextForToast = LocalContext.current

    Scaffold(
        topBar = {
//            FinanceTopAppBar(navController, contextForToast)
        },
        bottomBar = {
            BottomBar(navController, contextForToast)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth(1f)
                    .align(Alignment.TopCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Green.copy(0.5f),
                                Color.Green.copy(0.4f),
                                Color.Green.copy(0.3f),
                                Color.Green.copy(0.2f),
                                Color.White.copy(0.5f)
                            ),
                            startY = 0f,
                            endY = 1000f
                        )
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Green.copy(0.1f),
                                    Color.White.copy(0.1f),
                                    Color.White.copy(0.9f),
                                    Color.White.copy(0.9f),
                                    Color.White.copy(0.9f)
                                ),
                                startY = 0f,
                                endY = 1000f
                            )
                        ), contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Analysis",
                        modifier = Modifier
                            .padding(25.dp),
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 50.sp,
                            letterSpacing = 0.1.sp,
                            fontFamily = FontFamily.Default
                        )
                    )
                }


            }
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth(0.5f)
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.pie1),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .size(width = 300.dp, height = 300.dp)
                )
            }

        }
    }
}

