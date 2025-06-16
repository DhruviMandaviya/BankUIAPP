package com.example.dhruvi.bankinguiapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dhruvi.bankinguiapp.data.Card
import com.example.dhruvi.bankinguiapp.ui.theme.BlueEnd
import com.example.dhruvi.bankinguiapp.ui.theme.BlueStart
import com.example.dhruvi.bankinguiapp.ui.theme.GreenEnd
import com.example.dhruvi.bankinguiapp.ui.theme.GreenStart
import com.example.dhruvi.bankinguiapp.ui.theme.OrangeEnd
import com.example.dhruvi.bankinguiapp.ui.theme.OrangeStart
import com.example.dhruvi.bankinguiapp.ui.theme.PurpleEnd
import com.example.dhruvi.bankinguiapp.ui.theme.PurpleStart

val cards : List<Card> = listOf(
    Card(
        cardType="VISA",
        cardNumber="**** **** **** 3456",
        cardName="Saving",
        balance=157.78,
        color = getGradient(PurpleStart, PurpleEnd)
    ),
    Card(
        cardType="MASTER CARD",
        cardNumber="**** **** **** 3896",
        cardName="Business",
        balance=1907.78,
        color = getGradient(BlueStart, BlueEnd)
    ),
    Card(
        cardType="DEBIT CARD",
        cardNumber="**** **** **** 0236",
        cardName="Saving",
        balance=177.78,
        color = getGradient(OrangeStart, OrangeEnd)
    ),
    Card(
        cardType="REWORD CARD",
        cardNumber="**** **** **** 5710",
        cardName="Saving",
        balance=393.78,
        color = getGradient(GreenStart, GreenEnd)
    )
)

fun getGradient(startColor: Color, endColor:Color):Brush {
    return Brush.horizontalGradient(
        colors= listOf(startColor,endColor)
    )
}


@Composable
fun CardSection(selectedCardType:String, onCardSelected:(String)->Unit){
    LazyRow {
        items(cards.size)
        { index->
            CardItem(
                card = cards[index],
                isSelected = selectedCardType == cards[index].cardType,
                onCardSelected = onCardSelected
            )
        }
    }
}

@Composable
fun CardItem(
    card: Card,
    isSelected: Boolean,
    onCardSelected: (String) -> Unit
) {
    val image = when (card.cardType) {
        "MASTER CARD" -> painterResource(id = R.drawable.ic_mastercard)
        else -> painterResource(id = R.drawable.ic_visa)
    }

    val cardModifier = if (isSelected) {
        Modifier
            .offset(x = 2.dp, y = 2.dp)
            .shadow(
                elevation = 10.dp,
                shape = RoundedCornerShape(25.dp),
                ambientColor = Color.Black,
                spotColor = Color.Gray
            )
            .border(
                width = 3.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(25.dp)
            )
    } else {
        Modifier
    }

    Box(modifier = Modifier.padding(start = 8.dp, end = 16.dp)) {
        Column(
            modifier = Modifier
                .then(cardModifier)
                .clip(RoundedCornerShape(25.dp))
                .background(card.color)
                .width(250.dp)
                .height(160.dp)
                .clickable { onCardSelected(card.cardType) }
                .padding(vertical = 12.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = image,
                contentDescription = card.cardName,
                modifier = Modifier.height(20.dp)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Text(text = card.cardName, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = "$ ${card.balance}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Text(text = card.cardNumber, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}