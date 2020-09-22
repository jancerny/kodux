package id.cerny.jan.vuexko.sample

import id.cerny.jan.vuexko.navigation.NavigationMutations
import id.cerny.jan.vuexko.sample.databinding.ScreenAboutBinding
import id.cerny.jan.vuexko.sample.vuexko.store

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