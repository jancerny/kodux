package id.cerny.jan.vuexko

interface Action<S, M : Mutation<S>, A : Action<S, M, A>> {
    suspend fun exec(store: Store<S, M, A>)
}