package com.example.moneyhubandorid.screen.Analysis

import android.widget.Toast
import androidx.compose.animation.core.FloatTweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.moneyhubandorid.AppBar.BottomBar
import com.example.moneyhubandorid.AppBar.FinanceTopAppBar

@Composable
fun AnalysisScreen(navController: NavHostController) {
    val contextForToast = LocalContext.current

    var categorySelected by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            FinanceTopAppBar(navController, contextForToast)
        },
        bottomBar = {
            BottomBar(navController, contextForToast)
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                items(100) { index ->
                    // รายการข้อมูลที่แสดง
                    // หากต้องการให้ปุ่มอยู่ใต้รายการที่แสดง สามารถใส่ปุ่มลงในนี้ได้
                }
                // ทำการวางปุ่ม "หมวดหมู่" ที่นี่
                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        ElevatedButton(
                            onClick = {
                                categorySelected = "หมวดหมู่ที่เลือก"
                            }
                        ) {
                            Text("หมวดหมู่", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
    PieChart()

    // เรียกใช้งานฟังก์ชัน CategoryItemList และส่งค่า categorySelected เข้าไป
    CategoryItemList(categorySelected)
}


    @Composable
fun PieChart() {
    val context = LocalContext.current
    val point = listOf(10f, 40f, 25f, 85f, 100f, 65f)
    val color = listOf(
        Color.Blue,
        Color.Yellow,
        Color.Green,
        Color.Gray,
        Color.Red,
        Color.Cyan
    )
    val sum = point.sum()
    var startAngle = 0f
    val radius = 350f
    val rect = Rect(Offset(-radius, -radius), Size(2 * radius, 2 * radius))
    val path = Path()
    val angles = mutableListOf<Float>()
    var start by remember { mutableStateOf(false) }
    val sweepPre by animateFloatAsState(
        targetValue = if (start) 1f else 0f,
        animationSpec = FloatTweenSpec(duration = 1000)
    )

    Canvas(
        modifier = Modifier
            .fillMaxWidth()  // ทำให้ Canvas เต็มความกว้าง
            .padding(horizontal = 30.dp)  // ปรับระยะห่างของ Canvas ด้านซ้ายและด้านขวาเป็น 30dp และระยะห่างด้านซ้ายเป็น 50dp
            .padding(start = 76.dp)
            .aspectRatio(1f)  // ตั้ง Aspect ratio เป็น 1:1 เพื่อให้ Canvas เป็นสี่เหลี่ยมจตุรัส
            .padding(bottom = 70.dp) // กำหนดให้ Canvas อยู่กึงกลางในแนวตั้ง
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        val x = it.x - radius
                        val y = it.y - radius
                        var touchAngle = Math.toDegrees(Math.atan2(y.toDouble(), x.toDouble()))
                        if (x < 0 && y < 0 || x > 0 && y < 0) {
                            touchAngle += 360
                        }
                        val position =
                            getPositionFromAngle(touchAngle = touchAngle, angles = angles)
                        Toast
                            .makeText(context, "onTap: $position", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            }
    ) {
        translate(radius, radius) {
            start = true
            for ((i, p) in point.withIndex()) {
                val sweepAngle = p / sum * 360f
                path.moveTo(0f, 0f)
                path.arcTo(rect = rect, startAngle, sweepAngle * sweepPre, false)
                angles.add(sweepAngle)
                drawPath(path = path, color = color[i])
                path.reset()
                startAngle += sweepAngle
            }
        }
    }
}

private fun getPositionFromAngle(
    angles: List<Float>,
    touchAngle: Double
): Int {
    var totalAngle = 0f
    for ((i, angle) in angles.withIndex()) {
        totalAngle += angle
        if (touchAngle <= totalAngle) {
            return i
        }
    }
    return -1
}

@Composable
fun CategoryItemList(category: String) {
    val itemList = remember { generateItemList(category) } // เรียกใช้ฟังก์ชั่น generateItemList() เพื่อสร้างรายการสำหรับหมวดหมู่ที่กำหนด

    LazyColumn {
        items(itemList) { item ->
            // แสดงรายการที่สร้างจากฟังก์ชั่น generateItemList()
            Text(text = item, modifier = Modifier.padding(16.dp))
        }
    }
}

// ฟังก์ชั่นสร้างรายการตามหมวดหมู่ที่รับเข้ามา
private fun generateItemList(category: String): List<String> {
    // สร้างรายการตามหมวดหมู่
    return when (category) {
        "อาหาร" -> listOf("ข้าวผัด", "ส้มตำ", "ไก่ทอด")
        "การเดินทาง" -> listOf("เที่ยวเมือง", "ขับรถ", "เดินทางทะเล")
        else -> listOf() // หากไม่ระบุหมวดหมู่ใด ๆ ให้ส่งคืนรายการว่าง
    }
}


