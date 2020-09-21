package id.cerny.jan.vuexko.navigation

import id.cerny.jan.vuexko.Mutation

interface NavigationMutation<T : StateWithNavigation> : Mutation<T> {
    fun mutate(navigationState: NavigationState): NavigationState

    override fun invoke(state: T): T = state.copyNavigation(mutate(state.navigationState)) as T
}

object NavigationMutations {

    data class ShowScreen<T : StateWithNavigation>(val screen: Screen) : NavigationMutation<T> {

        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(
                currentScreen = screen,
                screenHistory = navigationState.screenHistory.plus(navigationState.currentScreen)
            )
    }

    class GoBack<T : StateWithNavigation> : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) = navigationState.copy(
            currentScreen = navigationState.screenHistory.lastOrNull() ?: Screen.None,
            screenHistory = navigationState.screenHistory.dropLast(1)
        )
    }

    data class ShowDialog<T : StateWithNavigation>(val dialog: Dialog) : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(dialogs = navigationState.dialogs.plus(dialog))
    }

    class HideDialog<T : StateWithNavigation> : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(dialogs = navigationState.dialogs.drop(1))
    }

    class ClearDialogs<T : StateWithNavigation> : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(dialogs = emptyList())
    }

    class ShowMessage<T : StateWithNavigation>(val message: Message) : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(messages = navigationState.messages.plus(message))
    }

    class HideMessage<T : StateWithNavigation> : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(messages = navigationState.messages.drop(1))
    }

    class ClearMessages<T : StateWithNavigation> : NavigationMutation<T> {
        override fun mutate(navigationState: NavigationState) =
            navigationState.copy(messages = emptyList())
    }

}