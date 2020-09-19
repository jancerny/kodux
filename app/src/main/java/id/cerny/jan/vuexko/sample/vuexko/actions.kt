package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.Action
import kotlinx.coroutines.delay

interface AppAction : Action<AppState>

object Actions {
    object Increment : AppAction {
        override suspend fun exec(store: AppStore) {
            store.commit(Mutations.SetProgress(true))
            delay(1000L)
            store.commit(Mutations.Increment)
            store.commit(Mutations.SetProgress(false))
        }
    }
}
