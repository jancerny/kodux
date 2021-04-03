package id.cerny.jan.kodux

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.*

class Store<S>(
    initialState: S,
    mutationsBufferCapacity: Int = 0,
    onMutationsBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
) {

    private val stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    private val mutationsFlow: MutableSharedFlow<Mutation<S>> = MutableSharedFlow(
        replay = 0,
        extraBufferCapacity = mutationsBufferCapacity,
        onBufferOverflow = onMutationsBufferOverflow
    )

    val state: StateFlow<S> get() = stateFlow

    init {
        scope.launch {
            mutationsFlow.scan(initialState) { state, mutation ->
                mutation(state)
            }.collect { newState ->
                stateFlow.value = newState
            }
        }
    }

    fun dispatch(action: Action<Store<S>>) {
        scope.launch {
            action.invoke(this@Store)
        }
    }

    fun commit(mutation: Mutation<S>) {
        scope.launch {
            mutationsFlow.emit(mutation)
        }
    }

}