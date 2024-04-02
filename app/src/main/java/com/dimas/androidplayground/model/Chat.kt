package com.dimas.androidplayground.model

import android.graphics.drawable.Drawable

data class Chat(
    val image: Drawable?,
    val username: String,
    val message: String,
    val unreadMessage: Int,
    val date: String
)