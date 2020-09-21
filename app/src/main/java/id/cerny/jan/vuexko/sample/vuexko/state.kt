package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.navigation.NavigationState
import id.cerny.jan.vuexko.navigation.StateWithNavigation

data class AppState(
    val progress: Boolean = false,
    val counter: Int = 0,
    override val navigationState: NavigationState = NavigationState(currentScreen = AppScreen.Home)
) : StateWithNavigation {
    override fun copyNavigation(navigationState: NavigationState): AppState =
        copy(navigationState = navigationState)
}