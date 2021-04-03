package id.cerny.jan.kodux.sample.kodux

import id.cerny.jan.kodux.navigation.Screen

sealed class AppScreen : Screen {
    object Home : AppScreen()
    object About : AppScreen()
}