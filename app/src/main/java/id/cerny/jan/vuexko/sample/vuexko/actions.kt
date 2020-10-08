package id.cerny.jan.vuexko.sample.vuexko

import id.cerny.jan.vuexko.Action
import kotlinx.coroutines.delay

typealias AppAction = Action<AppStore>

object Actions {


    fun Increment(): AppAction = { store ->
        store.commit(Mutations.SetProgress(true))
        delay(1000L)
        store.commit(Mutations.Increment)
        store.commit(Mutations.SetProgress(false))
    }
}

