package id.cerny.jan.kodux.sample.state

import id.cerny.jan.kodux.navigation.Screen

sealed class AppScreen : Screen {
    object Home : AppScreen()
    object About : AppScreen()
}