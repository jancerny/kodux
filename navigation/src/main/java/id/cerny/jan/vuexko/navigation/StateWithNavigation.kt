package id.cerny.jan.vuexko.navigation

interface StateWithNavigation {
    val navigationState: NavigationState

    fun copyNavigation(navigationState: NavigationState): StateWithNavigation
}