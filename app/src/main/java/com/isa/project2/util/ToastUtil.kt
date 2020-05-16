package com.isa.project2.util

import android.content.Context
import android.os.Message
import android.widget.Toast

fun tampilToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}