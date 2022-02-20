package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.*
import kotlin.test.Test
import kotlin.time.Duration.Companion.seconds

class CoroutinesTest {
    @OptIn(FlowPreview::class, DelicateCoroutinesApi::class)
    @Test
    fun coroutinesTest() {
        val flow = channelFlow<Int> {
            send(15)
        }.cancellable()
        val flow1 = channelFlow<Int> {
            trySendBlocking(12)
        }.cancellable()
        val flow2 = flow {
                emit(17)
        }.cancellable()
        runBlocking {
            val eventScope = this + CoroutineName("event scope") + Job()
            val globalFlow = flowOf(flow, flow1, flow2)
                .onCompletion { cause ->
                    println("COMPLETION: $cause")
                }
            val eventJob = eventScope.launch { globalFlow.collect() }
            globalFlow.shareIn(eventScope, SharingStarted.Lazily)

            launch {
                globalFlow.collect {
                    println("GOT1 $it")
                }
            }
            launch {
                globalFlow.collect {
                    println("GOT2 $it")
                }
            }
            delay(3.seconds)
            eventJob.cancelAndJoin()
            println("SLJ:DFFJSFDKJ:LKJLSDFKJL")
        }

    }

    @Test
    fun sharedFlow() {
        runBlocking {
            val flow = flowOf(1,2,3,4,5,6,7,8,9,0)
            val eventScope = this + CoroutineName("event scope") + Job()
            flow
                .onEach { println("GOTTTTT: $it") }
                .shareIn(eventScope, SharingStarted.Eagerly)
        }
    }

}
