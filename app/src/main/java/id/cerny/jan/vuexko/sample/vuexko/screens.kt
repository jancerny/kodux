package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.navigation.Screen

sealed class AppScreen : Screen {
    object Home : AppScreen()
    object About : AppScreen()
}