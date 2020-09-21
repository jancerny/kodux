package id.cerny.jan.vuexko.navigation

interface Screen {
    object None : Screen
}

interface Dialog

interface Message

data class NavigationState(
    val currentScreen: Screen = Screen.None,
    val dialogs: List<Dialog> = emptyList(),
    val messages: List<Message> = emptyList(),
    val screenHistory: List<Screen> = emptyList()
)