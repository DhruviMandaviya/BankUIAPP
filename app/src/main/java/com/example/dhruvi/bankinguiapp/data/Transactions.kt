package com.example.dhruvi.bankinguiapp.data

import androidx.compose.ui.graphics.vector.ImageVector

data class Transactions(
    val title: String,
    val date: String,
    val amount: Double,
    val icon: ImageVector,
    val cardType: String
)

