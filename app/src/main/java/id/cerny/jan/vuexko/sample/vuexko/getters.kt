package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.navigation.Dialog
import id.cerny.jan.vuexko.navigation.Message
import id.cerny.jan.vuexko.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

object Getters {
    val currentScreen: Flow<Screen> =
        store.state.map { it.navigationState.currentScreen }.distinctUntilChanged()

    val currentDialog: Flow<Dialog?> =
        store.state.map { it.navigationState.dialogs.firstOrNull() }.distinctUntilChanged()

    val currentMessage: Flow<Message?> =
        store.state.map { it.navigationState.messages.firstOrNull() }.distinctUntilChanged()
}