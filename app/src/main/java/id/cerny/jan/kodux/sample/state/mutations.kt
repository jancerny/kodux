package id.cerny.jan.kodux.sample.state

import id.cerny.jan.kodux.Mutation

typealias AppMutation = Mutation<AppState>

object Mutations {
    fun SetProgress(inProgress: Boolean): AppMutation = { state ->
        state.copy(progress = inProgress)
    }

    val Increment: AppMutation = { state ->
        state.copy(counter = state.counter + 1)
    }
}
