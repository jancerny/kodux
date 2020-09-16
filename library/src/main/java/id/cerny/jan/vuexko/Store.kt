package id.cerny.jan.vuexko

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class Store<S, M : Mutation<S>, A : Action<S, M, A>>(
    initialState: S,
    private val scope: CoroutineScope = GlobalScope
) {

    private val stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    private val mutationsChannel: BroadcastChannel<M> = BroadcastChannel(BUFFERED)

    private val mutations: Flow<M> get() = mutationsChannel.asFlow()

    val state: StateFlow<S> get() = stateFlow

    init {
        scope.launch {
            mutations.scan(initialState) { state, mutation ->
                mutation(state)
            }.collect { newState ->
                stateFlow.value = newState
            }
        }
    }

    fun dispatch(action: A) {
        scope.launch {
            action.exec(this@Store)
        }
    }

    fun commit(mutation: M) {
        scope.launch {
            mutationsChannel.send(mutation)
        }
    }

}