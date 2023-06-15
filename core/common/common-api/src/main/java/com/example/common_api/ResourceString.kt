package com.example.common_api

import android.content.Context
import androidx.annotation.StringRes


/** Wrapper around string message. For get string message call [format] method. */
sealed class ResourceString {
    /** Return formatted string message. */
    abstract fun format(context: Context): String
}

/** Wrapper for message from string resource. */
data class IdResourceString(@StringRes val id: Int) : ResourceString() {
    override fun format(context: Context): String = context.getString(id)
}
