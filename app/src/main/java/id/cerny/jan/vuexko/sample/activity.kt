package id.cerny.jan.vuexko.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import id.cerny.jan.vuexko.sample.databinding.ActivityMainBinding
import id.cerny.jan.vuexko.sample.vuexko.Actions
import id.cerny.jan.vuexko.sample.vuexko.AppAction
import id.cerny.jan.vuexko.sample.vuexko.AppState
import id.cerny.jan.vuexko.sample.vuexko.store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.increment.setOnClickListener {
            store.dispatch(Actions.Increment)
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