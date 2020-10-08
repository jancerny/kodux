package id.cerny.jan.vuexko.sample

import androidx.core.view.isVisible
import id.cerny.jan.vuexko.sample.databinding.ScreenHomeBinding
import id.cerny.jan.vuexko.sample.vuexko.Actions
import id.cerny.jan.vuexko.sample.vuexko.AppState
import id.cerny.jan.vuexko.sample.vuexko.store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeUI(override val binding: ScreenHomeBinding) : MainScreenUI {
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    init {
        binding.increment.setOnClickListener {
            store.dispatch(Actions.Increment())
        }

        scope.launch {
            store.state.collect {
                show(it)
            }
        }
    }

    private fun show(state: AppState) {
        binding.increment.isEnabled = !state.progress
        binding.currentValue.isVisible = !state.progress
        binding.progress.isVisible = state.progress
        binding.currentValue.text = state.counter.toString()
    }
}