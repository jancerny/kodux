package id.cerny.jan.kodux.sample

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.google.android.material.snackbar.Snackbar
import id.cerny.jan.kodux.navigation.*
import id.cerny.jan.kodux.sample.databinding.ActivityMainBinding
import id.cerny.jan.kodux.sample.databinding.ScreenAboutBinding
import id.cerny.jan.kodux.sample.databinding.ScreenHomeBinding
import id.cerny.jan.kodux.sample.state.AppScreen
import id.cerny.jan.kodux.sample.state.Getters
import id.cerny.jan.kodux.sample.state.store
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private lateinit var binding: ActivityMainBinding
    private var currentDialog: AlertDialog? = null
    private var currentSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        observeNavigationState()

        binding.navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> store.commit(NavigationMutations.ShowScreen(AppScreen.Home))
                R.id.about -> store.commit(NavigationMutations.ShowScreen(AppScreen.About))
            }

            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

    override fun onBackPressed() {
        store.commit(NavigationMutations.GoBack())
    }

    private fun observeNavigationState() {
        scope.launch {
            launch {
                Getters.currentScreen.collect {
                    showScreen(it)
                }
            }

            launch {
                Getters.currentDialog.collect {
                    showDialog(it)
                }
            }

            launch {
                Getters.currentMessage
                    .collect {
                        showMessage(it)
                    }
            }
        }
    }

    private fun showScreen(screen: Screen) {
        when (screen) {
            AppScreen.Home -> setContentUI(HomeUI(ScreenHomeBinding.inflate(layoutInflater)))
            AppScreen.About -> setContentUI(AboutUI(ScreenAboutBinding.inflate(layoutInflater)))
            is Screen.None -> {
                screen.lastScreen?.let { lastScreen ->
                    store.commit(NavigationMutations.ShowScreen(lastScreen, StackOptions.CLEAR_ALL))
                    finish()
                }
            }
        }
    }

    private fun showDialog(dialog: Dialog?) {
        currentDialog?.dismiss()
        currentDialog = null

        val d = dialog as? AppDialog ?: return

        currentDialog = AlertDialog.Builder(this)
            .setTitle(d.title)
            .setMessage(d.message)
            .setNeutralButton("Ok") { di, _ ->
                store.commit(NavigationMutations.HideDialog())
            }
            .create()
        currentDialog?.show()
    }

    private fun showMessage(message: Message?) {
        currentSnackbar?.dismiss()
        currentSnackbar = null

        val m = message as? AppMessage ?: return

        currentSnackbar = Snackbar.make(binding.root, m.message, Snackbar.LENGTH_INDEFINITE)
        currentSnackbar?.show()

        scope.launch {
            delay(2000L)
            store.commit(NavigationMutations.HideMessage())
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