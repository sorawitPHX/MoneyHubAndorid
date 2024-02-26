@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.project_1

import android.content.Context
import android.widget.ImageButton
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import com.example.moneyhubandorid.R
import com.example.moneyhubandorid.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen() {
    val contextForToast = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val navigationItems = listOf(
        Screen.Home,
        Screen.Finance,
        Screen.Analysis,
        Screen.Profile,
    )
    var selectedScreen by remember {
        mutableStateOf(0) // or use mutableStateOf(0)
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ){
        CenterAlignedTopAppBar(
            title = {

                 },
            actions = {
                // Notificotions icon
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


            },//end action
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Blue.copy(alpha = 0.3f)
            )
        )


Box(
    modifier = Modifier.fillMaxWidth()

)
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(vertical = 16.dp)
        ){


            ExpenseIconButton(
                iconRes = R.drawable.fast_food,
                label = "อาหาร",
                onClick = { /*TODO*/ }
            )

            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "รายวัน",
                onClick = { /*TODO*/ }
            )


            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "การจราจร",
                onClick = { /*TODO*/ }
            )

            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "ทางสังคม",
                onClick = { /*TODO*/ }
            )

        }
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(vertical = 16.dp)
        ){


            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "ที่อยู่อาศัย",
                onClick = { /*TODO*/ }
            )

            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "ของขวัญ",
                onClick = { /*TODO*/ }
            )

            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "สื่อสาร",
                onClick = { /*TODO*/ }
            )

            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "เสื้อผ้า",
                onClick = { /*TODO*/ }
            )

        }
        Row {


            ExpenseIconButton(
                iconRes = R.drawable.ic_launcher_foreground,
                label = "การตั้งค่า",
                onClick = { /*TODO*/ }
            )


        }
    }

}

@Composable
fun ExpenseIconButton(
    iconRes: Int,
    label: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(100.dp)
    ) {
        Box(
            modifier = Modifier
//                .border(
//                    width = 1.dp,
//                    color = Color.Gray.copy(alpha = 0.5f),
//                    shape = RoundedCornerShape(10.dp)
//                )
                .padding(10.dp)
                .size(120.dp)
                .aspectRatio(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                //horizontalAlignment = Alignment.CenterHorizontally // เพิ่ม horizontalAlignment เพื่อจัดให้เนื้อหาอยู่กึ่งกลางในแนวนอน
            ) {
                Image(
                    painter = painterResource(iconRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = label,
                    fontSize = 15.sp,
                    color = Color.Black,
                )
            }
        }
    }
}

//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val ( secondText, catImage) = createRefs()
//
//
//
//        Text(
//            text = "We are cats!",
//            style = TextStyle(Color.Black),
//            fontSize = 30.sp,
//            modifier = Modifier
//                .padding(5.dp)
//                .constrainAs(secondText) {
//                    centerHorizontallyTo(parent)
//                    top.linkTo(catImage.bottom)
//                }
//        )
//
//        Image(
//            painter = painterResource(id = R.drawable.cartoon_cat),
//            contentDescription = null,
//            contentScale = ContentScale.Fit,
//            modifier = Modifier
//                .size(300.dp)
//                .constrainAs(catImage) {
//                    centerHorizontallyTo(parent)
//                    top.linkTo(catImage.top)
//                }
//        )
//
//
//    }
//    ConstraintLayout(
//        modifier = Modifier.fillMaxSize()
//    ) {
//        val (secondText, catImage) = createRefs()
//
//        Text(
//            text = "We are cats!",
//            style = TextStyle(Color.Black),
//            fontSize = 30.sp,
//            modifier = Modifier
//                .padding(5.dp)
//                .constrainAs(secondText) {
//                    centerHorizontallyTo(parent)
//                    top.linkTo(catImage.bottom)
//                }
//        )

//        ImageButton(
//            onClick = {
//                Toast.makeText(contextForToast, "คุณกำลังคลิกที่รูปภาพ", Toast.LENGTH_SHORT).show()
//            },
//            content = {
//                Image(
//                    painter = painterResource(id = R.drawable.cartoon_cat),
//                    contentDescription = null,
//                    contentScale = ContentScale.Fit,
//                    modifier = Modifier
//                        .size(300.dp)
//                )
//                Text(
//                    text = "กดที่รูปภาพ",
//                    color = Color.White,
//                    modifier = Modifier
//                        .align(Alignment.BottomCenter)
//                        .padding(8.dp)
//                )
//            },
//            modifier = Modifier
//                .size(300.dp)
//                .constrainAs(catImage) {
//                    centerHorizontallyTo(parent)
//                    top.linkTo(parent.top)
//                }
//        )





