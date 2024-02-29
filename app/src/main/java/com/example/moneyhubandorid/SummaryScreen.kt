package com.example.moneyhubandorid

import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun Summary() {
    val contextForToast = LocalContext.current.applicationContext

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .background( color = Color.Green.copy(alpha = 0.1f),
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        // Header Button with Icon
        ElevatedButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp),
            onClick = {
                Toast.makeText(
                    contextForToast,
                    "Summary",
                    Toast.LENGTH_LONG
                ).show()
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "การเงิน", color = Color.Black)
            }
        }

        // Spacer to create separation
        Spacer(modifier = Modifier.height(16.dp))

        // Summary Text
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 100.dp,
                    bottom = 20.dp
                )
                .background(
                    color = Color.Green.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(16.dp),
            text = "ยอดรวมทุกบัญชี : 0 ฿\nรายได้ : 0 ฿\nค่าใช้จ่าย : 0 ฿",
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        // Row with Buttons and Icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 250.dp,
                    bottom = 20.dp
                )
        ) {
            ElevatedButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                onClick = {
                    Toast.makeText(
                        contextForToast,
                        "ค่าใช้จ่าย",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.shopping_cart), // Replace with your icon resource
                        contentDescription = null, // Set a proper content description
                        tint = Color.Black, // Icon color
                        modifier = Modifier.size(24.dp) // Icon size
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "+ ค่าใช้จ่าย", color = Color.Black)
                }
            }

            ElevatedButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                onClick = {
                    Toast.makeText(
                        contextForToast,
                        "รายได้",
                        Toast.LENGTH_LONG
                    ).show()
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.money), // Replace with your icon resource
                        contentDescription = null, // Set a proper content description
                        tint = Color.Black, // Icon color
                        modifier = Modifier.size(24.dp) // Icon size
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "+ รายได้", color = Color.Black)
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 350.dp,
                    bottom = 20.dp
                )
                .background(
                    color = Color.Green.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Use a Box to position the image at the top-left corner
            Box(
                modifier = Modifier
                    .size(60.dp)
            ) {
                // Icon
                Image(
                    painter = painterResource(id = R.drawable.book),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Text
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    ) {
                        append(" สมุดบันทึกเริ่มต้น\n")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append(" จำนวนเงินทั้งหมด : 0 ฿\n")
                    }

                    withStyle(
                        style = SpanStyle(
                            color = Color.Black
                        )
                    ) {
                        append(" รายได้ : 0 ฿\n")
                        append(" ค่าใช้จ่าย : 0 ฿\n")
                    }
                },
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp)
            )

        }






    }
}




