package id.cerny.jan.kodux.sample.state

import id.cerny.jan.kodux.navigation.NavigationState
import id.cerny.jan.kodux.navigation.StateWithNavigation

data class AppState(
    val progress: Boolean = false,
    val counter: Int = 0,
    override val navigationState: NavigationState = NavigationState(currentScreen = AppScreen.Home)
) : StateWithNavigation {
    override fun copyNavigation(navigationState: NavigationState): AppState =
        copy(navigationState = navigationState)
}