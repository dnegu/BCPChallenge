package pe.com.test.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

fun ViewModel.launch(
    context: CoroutineDispatcher = Dispatchers.Main,
    block: suspend CoroutineScope.() -> Unit
) {
    viewModelScope.launch(block = block, context = context)
}