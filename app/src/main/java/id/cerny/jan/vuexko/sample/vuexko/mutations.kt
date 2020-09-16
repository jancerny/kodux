package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.Mutation

typealias AppMutation = Mutation<AppState>

object Mutations {
    fun SetProgress(inProgress: Boolean): AppMutation = { state ->
        state.copy(progress = inProgress)
    }

    val Increment: AppMutation = { state ->
        state.copy(counter = state.counter + 1)
    }
}
