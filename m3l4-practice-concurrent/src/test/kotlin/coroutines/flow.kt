package coroutines

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import org.junit.Test

class FlowTest {

    @Test
    fun flowTest() {
        runBlocking {
            val sf = MutableSharedFlow<Int>(
                extraBufferCapacity = 1024,
                onBufferOverflow = BufferOverflow.DROP_OLDEST
            )
            val job = sf.onEach { println("SF: $it") }.launchIn(this)
            delay(1)
            sf.tryEmit(456)
            (1..20).forEach {
                println("send: $it")
                sf.emit(it)
            }
            sf.tryEmit(457)
            delay(1) // Не успевает отработать без задержки
            job.cancelAndJoin()
        }
    }
}
