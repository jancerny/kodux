package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.Action
import id.cerny.jan.vuexko.Store
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

interface AppAction : Action<AppState, AppMutation, AppAction>

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
