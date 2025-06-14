package com.example.dhruvi.bankinguiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.material.icons.rounded.Cake
import androidx.compose.material.icons.rounded.Coffee
import androidx.compose.material.icons.rounded.Liquor
import androidx.compose.material.icons.rounded.LocalCafe
import androidx.compose.material.icons.rounded.LocalPizza
import androidx.compose.material.icons.rounded.Movie
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dhruvi.bankinguiapp.data.Transactions

val allTransactionsList= listOf(
    Transactions("Starbucks", "June 8, 2025", -6.50, Icons.Rounded.LocalCafe,"VISA"),
    Transactions("Cash Back", "June 6, 2025", 6.55, Icons.Rounded.AttachMoney,"VISA"),
    Transactions("Netflix", "June 5, 2025", -15.99, Icons.Rounded.Movie,"VISA"),
    Transactions("Tims", "June 10, 2025", -5.50, Icons.Rounded.Coffee,"VISA"),
    Transactions("Grocery", "June 6, 2025", -78.50, Icons.Rounded.ShoppingCart,"MASTER CARD"),
    Transactions("Starbucks", "June 8, 2025", -6.50, Icons.Rounded.LocalCafe,"VISA"),
    Transactions("Pizza Hut", "June 4, 2025", -55.50, Icons.Rounded.LocalPizza,"MASTER CARD"),
    Transactions("Birthday Party", "June 6, 2025", -500.00, Icons.Rounded.Cake,"VISA"),
    Transactions("Cash Back", "June 6, 2025", 6.50, Icons.Rounded.AttachMoney,"MASTER CARD"),
    Transactions("LCBO", "June 6, 2025", -1500.00, Icons.Rounded.Liquor,"MASTER CARD"),
)

@Composable
fun TransactionsSection(selectedFilter : String ) {
    val filteredList = remember(selectedFilter) {
        if (selectedFilter.uppercase() == "ALL") {
            allTransactionsList
        } else {
            allTransactionsList.filter {
                it.cardType.equals(selectedFilter, ignoreCase = true)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        ) {
        Text(
            text = "Recent Transactions",
                fontSize = 24.sp,
                color=MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
        )
        if (filteredList.isEmpty()) {
            Text(
                text = "No transactions for $selectedFilter",
                modifier = Modifier.padding(horizontal = 16.dp),
                color = Color.Gray
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp)
            ) {
                items(filteredList) { transaction ->
                    TransactionItem(transaction)
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.4f), thickness = 0.5.dp)
                }
            }
        }
    }
}


@Composable
fun TransactionItem(transaction: Transactions) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = transaction.icon,
            contentDescription = transaction.title,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                    shape = CircleShape
                )
                .padding(6.dp)
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = transaction.title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = transaction.date,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }

        Text(
            text = "${if (transaction.amount < 0) "-" else "+"}$${kotlin.math.abs(transaction.amount)}",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (transaction.amount < 0) Color.Red else Color(0xFF4CAF50),
                fontWeight = FontWeight.Bold
            )
        )
    }
}