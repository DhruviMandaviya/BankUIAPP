package com.example.dhruvi.bankinguiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Wallet
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dhruvi.bankinguiapp.data.BottomNavigation

val items =listOf(
    BottomNavigation("Home", Icons.Rounded.Home,0),
    BottomNavigation("Wallet", Icons.Rounded.Wallet,2),
    BottomNavigation("Notifications", Icons.Rounded.Notifications,4),
    BottomNavigation("Accounts", Icons.Rounded.AccountBox,2),

)

@Preview(showBackground = true)
@Composable
fun BottomNavigationBar() {
    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == 0,
                    onClick = {},
                    icon = {
                        BadgedBox(badge = {
                            if (item.badgeCount > 0) {
                                Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.secondary, shape = CircleShape).padding(horizontal = 5.dp, vertical = 1.dp))
                                {
                                    Text(text = item.badgeCount.toString(),color = MaterialTheme.colorScheme.secondaryContainer)
                                }

                            }
                        }
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    },
                    label = {
                        Text(text = item.title,
                            color = MaterialTheme.colorScheme.onBackground)
                    },
                )
            }
        }
    }
}