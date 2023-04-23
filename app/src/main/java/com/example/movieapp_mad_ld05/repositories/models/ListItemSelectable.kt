package com.example.movieapp_mad_ld05.repositories.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ListItemSelectable(
    val title: String,
    initialIsSelected: Boolean = false
) {
    var isSelected by mutableStateOf(initialIsSelected)
}