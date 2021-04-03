package id.cerny.jan.kodux.sample.kodux

import id.cerny.jan.kodux.Action
import id.cerny.jan.kodux.sample.state.AppStore
import id.cerny.jan.kodux.sample.state.Mutations
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

