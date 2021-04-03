package id.cerny.jan.kodux.sample

import id.cerny.jan.kodux.navigation.NavigationMutations
import id.cerny.jan.kodux.sample.databinding.ScreenAboutBinding
import id.cerny.jan.kodux.sample.state.store

class AboutUI(override val binding: ScreenAboutBinding) : MainScreenUI {
    init {
        binding.showMessage.setOnClickListener {
            store.commit(NavigationMutations.ShowMessage(AppMessage("Test message")))
        }

        binding.showDialog.setOnClickListener {
            store.commit(NavigationMutations.ShowDialog(AppDialog("Hello", "Test dialog")))
        }
    }
}