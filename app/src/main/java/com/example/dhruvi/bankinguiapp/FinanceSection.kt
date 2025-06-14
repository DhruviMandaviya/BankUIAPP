package com.example.dhruvi.bankinguiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.StarHalf
import androidx.compose.material.icons.rounded.MonetizationOn
import androidx.compose.material.icons.rounded.Money
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dhruvi.bankinguiapp.data.Finance
import com.example.dhruvi.bankinguiapp.ui.theme.BlueStart
import com.example.dhruvi.bankinguiapp.ui.theme.GreenStart
import com.example.dhruvi.bankinguiapp.ui.theme.OrangeStart
import com.example.dhruvi.bankinguiapp.ui.theme.PurpleStart

val financeList:List<Finance> = listOf(
    Finance(icon = Icons.AutoMirrored.Rounded.StarHalf,
        name = "My\nBusiness",
        backgroundColor = OrangeStart),

    Finance(icon = Icons.Rounded.Wallet,
        name = "My\nWallet",
        backgroundColor = BlueStart),

    Finance(icon = Icons.Rounded.MonetizationOn,
        name = "My\nTransctions",
        backgroundColor = PurpleStart),

    Finance(icon = Icons.Rounded.Money,
        name = "My\nSaving",
        backgroundColor = GreenStart)
)
@Preview
@Composable
fun FinanceSection(){

    Column() {
        Text(text = "Finance",
            fontSize = 24.sp,
            color=MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
        LazyRow()
        {
            items(financeList.size){
                FinanceItem(it)
            }
        }


    }

}

@Composable
fun FinanceItem(index: Int)
{

    val finace=financeList[index]
    var lastpadding=0.dp
    if (index== financeList.size-1)
    {
        lastpadding=16.dp
    }
    Box(modifier = Modifier.padding(start = 16.dp, end = lastpadding))
    {
        Column(
            modifier = Modifier.clip(RoundedCornerShape(25.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer)
                .size(120.dp)
                .clickable {}
                .padding(13.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.clip(RoundedCornerShape(16.dp))
                .background(finace.backgroundColor)
                .padding(5.dp)
            )
            {
                Icon(
                    imageVector = finace.icon,
                    contentDescription = finace.name,
                    tint = Color.White,
                )
            }
            Text(text=finace.name,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp)
        }
    }

}