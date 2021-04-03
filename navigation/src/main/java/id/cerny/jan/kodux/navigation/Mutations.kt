@file:Suppress("Unused", "FunctionName")

package id.cerny.jan.kodux.navigation

import id.cerny.jan.kodux.Mutation

@Suppress("Unchecked_Cast")
fun <T : StateWithNavigation> NavigationMutation(mutate: (NavigationState) -> NavigationState): Mutation<T> =
    { state ->
        state.copyNavigation(mutate(state.navigationState)) as T
    }

object NavigationMutations {

    fun <T : StateWithNavigation> ShowScreen(
        screen: Screen,
        stackOptions: StackOptions = StackOptions.NONE
    ) =
        NavigationMutation<T> { navigationState ->
            val history = when (stackOptions) {
                StackOptions.NONE -> navigationState.screenHistory.plus(navigationState.currentScreen)
                StackOptions.CLEAR_ALL -> emptyList()
                StackOptions.CLEAR_UNTIL_SAME -> navigationState.screenHistory.takeWhile { it != screen }
            }
            navigationState.copy(
                currentScreen = screen,
                screenHistory = history
            )
        }

    fun <T : StateWithNavigation> GoBack() =
        NavigationMutation<T> { navigationState ->
            navigationState.copy(
                currentScreen = navigationState.screenHistory.lastOrNull() ?: Screen.None(
                    navigationState.currentScreen
                ),
                screenHistory = navigationState.screenHistory.dropLast(1)
            )
        }

    fun <T : StateWithNavigation> ShowDialog(dialog: Dialog) =
        NavigationMutation<T> { navigationState ->
            navigationState.copy(dialogs = navigationState.dialogs.plus(dialog))
        }

    fun <T : StateWithNavigation> HideDialog() =
        NavigationMutation<T> { navigationState ->
            navigationState.copy(dialogs = navigationState.dialogs.drop(1))
        }

    fun <T : StateWithNavigation> ClearDialogs() = NavigationMutation<T> { navigationState ->
        navigationState.copy(dialogs = emptyList())
    }

    fun <T : StateWithNavigation> ShowMessage(message: Message) =
        NavigationMutation<T> { navigationState ->
            navigationState.copy(messages = navigationState.messages.plus(message))
        }

    fun <T : StateWithNavigation> HideMessage() =
        NavigationMutation<T> { navigationState ->
            navigationState.copy(messages = navigationState.messages.drop(1))
        }

    fun <T : StateWithNavigation> ClearMessages() =
        NavigationMutation<T> { navigationState ->
            navigationState.copy(messages = emptyList())
        }

}