import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.test.runTest
import org.junit.Test

class Actor {
    @OptIn(ExperimentalCoroutinesApi::class, ObsoleteCoroutinesApi::class)
    @Test
    fun actor() = runTest {
        var cntr = 0
        val actor = actor<Int> {
            for(msg in channel) {
                cntr += msg
            }
        }
        actor.send(15)
        println("rrr $cntr")
        actor.close()
    }
}
