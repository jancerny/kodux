package id.cerny.jan.vuexko.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import id.cerny.jan.vuexko.navigation.NavigationMutations
import id.cerny.jan.vuexko.navigation.Screen
import id.cerny.jan.vuexko.sample.databinding.ActivityMainBinding
import id.cerny.jan.vuexko.sample.databinding.ScreenAboutBinding
import id.cerny.jan.vuexko.sample.databinding.ScreenHomeBinding
import id.cerny.jan.vuexko.sample.vuexko.AppScreen
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

        scope.launch {
            store.state.collect {
                showScreen(it.navigationState.currentScreen)
            }
        }

        binding.navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> store.commit(NavigationMutations.ShowScreen(AppScreen.Home))
                R.id.about -> store.commit(NavigationMutations.ShowScreen(AppScreen.About))
            }

            true
        }
    }

    private fun showScreen(screen: Screen) {
        when (screen) {
            AppScreen.Home -> setContentUI(HomeUI(ScreenHomeBinding.inflate(layoutInflater)))
            AppScreen.About -> setContentUI(AboutUI(ScreenAboutBinding.inflate(layoutInflater)))
        }

    }

    private fun setContentUI(ui: MainScreenUI) {
        binding.content.removeAllViews()
        binding.content.addView(ui.binding.root)
    }

}

interface MainScreenUI {
    val binding: ViewBinding
}