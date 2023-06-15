package com.example.ui_core.extensions

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun bindingLifecycleError(): Nothing = throw IllegalStateException(
    "This property is only valid between onCreateView and onDestroyView."
)

inline fun Fragment.launchWhenViewStarted(
    crossinline block: suspend CoroutineScopeWrapper.() -> Unit
) = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
    CoroutineScopeWrapper(this).block()
}
fun Fragment.runWithArgumentsOrSkip(block: (bundle: Bundle) -> Unit) =
    this.arguments?.let(block) ?: Unit

class CoroutineScopeWrapper(
    val scope: CoroutineScope,
    var errorHandler: (Throwable) -> Unit = globalErrorHandler
) {
    fun <T> Flow<T>.observe(action: suspend (T) -> Unit) = this
        .onEach(action)
        .catch { errorHandler(it) }
        .launchIn(scope)

    companion object {
        var globalErrorHandler: (Throwable) -> Unit = { throw it }
    }
}

fun DialogFragment.showOnlyOne(fragmentManager: FragmentManager) {
    if (fragmentManager.findFragmentByTag(this.javaClass.name) == null)
        this.show(fragmentManager, this.javaClass.name)
}


fun DialogFragment?.tuneLyricsDialog() {
    this?.dialog?.let { dialog ->
        val window = dialog.window ?: return
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}