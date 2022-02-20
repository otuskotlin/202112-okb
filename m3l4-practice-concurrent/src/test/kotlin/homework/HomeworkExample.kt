package homework

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.util.concurrent.Executors
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class HomeworkExample {

    @Test
    fun addElement() {
        val us = UserSessions()
        us.add(UserAdminSession("petr"))
        assertEquals(1, us.sessions.size)
    }

    @Test
    fun sequenceMethod() = runTest {
        val us = UserSessions()

        val userSequence = (1..25000)
            .asSequence()
            .map { UserAdminSession("admin-$it") }
            .filter { it.login.contains("2") }
            .take(5)

        us.addAll(userSequence)
        assertEquals(5, us.sessions.size)
    }

    @Test
    fun flowMethod() = runTest {
        val us = UserSessions()

        val userFlow = (1..25000)
            .asFlow()
            .filter { it % 3 == 0 }
            .map { UserAdminSession("admin-$it") }
            .filter { it.login.contains("1") }
            .drop(8)
            .take(5)

        us.addAll(userFlow)
        assertEquals(5, us.sessions.size)
    }

    @Test
    fun flowDelayed() = runTest {
        val us = UserSessions()

        val userFlow = (1..25000)
            .asFlow()
            .filter { it % 3 == 0 }
            .map {
                async() {
                    delay(3000)
                    UserAdminSession("admin-$it")
                }
            }
            .buffer(30)
            .drop(8)
            .take(5)
            .map { it.await() }

        us.addAll(userFlow)
        assertEquals(5, us.sessions.size)
    }

    @Test
    fun flowBlocking() = runTest {
        val us = UserSessions()
        val blockedContext = Dispatchers.IO + CoroutineName("blocking") + Job()

        val userFlow = (1..25000)
            .asFlow()
            .buffer(30)
            .filter { it % 3 == 0 }
            .map {
                println("map($it): ${Thread.currentThread().name}")
                async(blockedContext) {
                    println("async($it): ${Thread.currentThread().name}")
                    Thread.sleep(3000)
                    val u = UserAdminSession("admin-$it")
                    println("thread($it): ${Thread.currentThread().name} - done")
                    u
                }
            }
            .drop(8)
            .take(5)
            .buffer(5) // В буфере происходит ожидание результата
            .map { it.await() }

        us.addAll(userFlow)
        assertEquals(5, us.sessions.size)
    }
}

class UserSessions {
    private val userSessionsSet: MutableSet<UserSession> = mutableSetOf()

    val sessions: Set<UserSession>
        get() = userSessionsSet.toSet()

    fun add(userSession: UserSession) {
        userSessionsSet.add(userSession)
    }

    suspend fun addAll(users: Sequence<UserSession>) {
        userSessionsSet.addAll(users)
    }

    suspend fun addAll(users: Flow<UserSession>) {
        users.collect { user ->
            userSessionsSet.add(user)
        }
    }

}

sealed class UserSession(val login: String = "") {}
class UserAdminSession(login: String) : UserSession(login)
