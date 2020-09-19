package id.cerny.jan.vuexko

interface Action<S> {
    suspend fun exec(store: Store<S>)
}