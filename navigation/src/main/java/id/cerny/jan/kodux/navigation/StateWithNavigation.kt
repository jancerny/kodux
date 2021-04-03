package id.cerny.jan.kodux.navigation

interface StateWithNavigation {
    val navigationState: NavigationState

    fun copyNavigation(navigationState: NavigationState): StateWithNavigation
}