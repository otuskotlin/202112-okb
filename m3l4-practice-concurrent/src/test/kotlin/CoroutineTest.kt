import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineTest {
    @Test
    fun coroutineTest() {
        runBlocking {  }
        val setWrapper = SetWrapper()
        val admin = AdminUser("admin-1")
        setWrapper.add(admin)
        assertEquals("admin-1", setWrapper.users.first().login)
    }

    @Test
    fun sequenceTest() {
        val setWrapper = SetWrapper()
        val sequence = (1..25000)
            .asSequence()
            .filter { it % 3 == 0 }
            .map { AdminUser("admin-$it") }
            .filter { it.login.contains("2") }
            .drop(30)
            .take(5)
        setWrapper.addAll(sequence)
        assertEquals(5, setWrapper.users.size)
    }

    @Test
    fun flowTest() = runTest {
        val setWrapper = SetWrapper()
        val flow = (1..25000)
            .asFlow()
            .filter { it % 3 == 0 }
            .map { AdminUser("admin-$it") }
            .filter { it.login.contains("2") }
            .drop(30)
            .take(5)
        setWrapper.addAll(flow)
        assertEquals(5, setWrapper.users.size)
    }

    @Test
    fun flowDelayTest() = runTest {
        val setWrapper = SetWrapper()
        val flow = (1..25000)
            .asFlow()
            .filter { it % 3 == 0 }
            .map {
                async {
                    delay(3.seconds)
                    AdminUser("admin-$it")
                }
            }
            .drop(30)
            .take(5)
            .map { it.await() }
        setWrapper.addAll(flow)
        assertEquals(5, setWrapper.users.size)
    }

    @Test
    fun flowSleepTest() = runTest {
        val setWrapper = SetWrapper()
        val flow = (1..25000)
            .asFlow()
            .filter { it % 3 == 0 }
            .map {
                println("map: ${Thread.currentThread().name}")
                async(Dispatchers.IO) {
                    println("async: ${Thread.currentThread().name}")
                    Thread.sleep(3000)
                    AdminUser("admin-$it")
                }
            }
            .drop(30)
            .take(5)
            .buffer(5)
            .map { it.await() }
        setWrapper.addAll(flow)
        assertEquals(5, setWrapper.users.size)
    }
}

class SetWrapper(
    private val mutableUsers: MutableSet<User> = mutableSetOf()
) {
    val users: Set<User>
        get() = mutableUsers.toSet()

    fun add(user: User) {
        mutableUsers.add(user)
    }

    fun addAll(sequence: Sequence<User>) {
        mutableUsers.addAll(sequence)
    }

    suspend fun addAll(flow: Flow<User>) {
        flow.collect {
            mutableUsers.add(it)
        }
    }

}

sealed class User(val login: String)
class AdminUser(login: String) : User(login)
