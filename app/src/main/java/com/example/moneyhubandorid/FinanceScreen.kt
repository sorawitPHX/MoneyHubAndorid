package com.example.project_1

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneyhubandorid.Screen

@Composable
fun FinanceScreen() {
    val contextForToast = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Row {
            IconButton(
                onClick = {
                    Toast.makeText(contextForToast, "ยกเลิกการแก้ไข", Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
            ) {
                Icon(Icons.Default.Cancel, contentDescription = "ค่าใช้จ่าย")
            }
            Button(onClick = {
                Toast.makeText(contextForToast,"ค่าใช้จ่าย", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text("ค่าใช้จ่าย")
            }
            Spacer(modifier = Modifier.width(8.dp)) // ตัวเว้นวรรค
            Button(onClick = {
                Toast.makeText(contextForToast,"รายได้", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text("รายได้")
            }
            IconButton(
                onClick = {
                    Toast.makeText(contextForToast, "บันทึกข้อมูล", Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier.size(48.dp) // กำหนดขนาดของปุ่ม
            ) {
                Icon(Icons.Default.Check, contentDescription = "รายได้")
            }
        }

    }
}