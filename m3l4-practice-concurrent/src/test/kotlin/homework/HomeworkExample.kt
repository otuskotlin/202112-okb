package homework

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
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
    fun flowBlocking() = runTest {
        val us = UserSessions()

        val userFlow = (1..25000)
            .asFlow()
            .filter { it % 3 == 0 }
            .map {
                println("map: ${Thread.currentThread().id}")
                async() {
                    withContext(Dispatchers.IO) {
                        println("async: ${Thread.currentThread().id}")
                        Thread.sleep(3000)
                        val u = UserAdminSession("admin-$it")
                        println("thread: ${Thread.currentThread().id} - done")
                        u
                    }
                }
            }
            .buffer(30)
//            .flowOn(Dispatchers.IO)
            .drop(8)
            .take(5)
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
