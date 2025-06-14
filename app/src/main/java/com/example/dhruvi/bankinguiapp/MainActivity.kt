package com.example.dhruvi.bankinguiapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.zIndex
import com.example.dhruvi.bankinguiapp.ui.theme.BankingUIAPPTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BankingUIAPPTheme {
                SetBarColor(color = MaterialTheme.colorScheme.background)
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = MaterialTheme.colorScheme.background) {
                    innerPadding ->
                    HomeScreen()
                    Text(text = "Hello", modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    private fun SetBarColor(color: Color) {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setSystemBarsColor(color = color)
        }
    }
}

@Preview
@Composable
fun HomeScreen() {
    var selectedCardType by remember { mutableStateOf("ALL") }
    Scaffold(
        bottomBar = {
            BottomNavigationBar()

        }
    ) {
        padding->
        Box( modifier = Modifier
            .fillMaxSize()
            .padding(padding))
        {
            Column(
                modifier=Modifier
                    .fillMaxSize()
                    .zIndex(0f)
            )
            {
                WalletSection()
                CardSection(
                    selectedCardType = selectedCardType,
                    onCardSelected = { cardType ->
                        selectedCardType = if (selectedCardType == cardType) "ALL" else cardType
                    }
                )
                FinanceSection()
                TransactionsSection(selectedFilter=selectedCardType)
            }
                CurrenciesSection()
        }
    }
}
