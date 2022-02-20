package coroutine_exceptions

import kotlinx.coroutines.*
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException
import kotlin.test.assertFails

@OptIn(ExperimentalCoroutinesApi::class)
class CoroutineExceptions {

    @Test
    fun mustFail(): Unit {
        assertFails {
            runTest {
                launch {
                    launch {
                        try {
                            throw IllegalArgumentException()
                        } finally {
                            println("IllegalArgumentException")
                        }
                    }
                    launch {
                        try {
                            throw IOException()
                        } finally {
                            println("IOException")
                        }
                    }
                }
            }
        }
    }

    @Test
    fun coroutineExceptions() = runTest {
        sequence<Int> {

        }

        with(CoroutineScope(CoroutineName("exception!") + Job())) {
//        with(CoroutineScope(CoroutineName("exception!") + Job())) {
            val job = launch { // root coroutine with launch
//                try {
                println("Throwing exception from launch")
                throw IndexOutOfBoundsException() // Will be printed to the console by Thread.defaultUncaughtExceptionHandler
//                } catch (e: Throwable) {
//                    println("CAUGHT")
//                } finally {
//                    withContext(NonCancellable) {
//                        println("FINAL")
//                    }
//                }
            }
            job.join()
            println("Joined failed job")
            val deferred = GlobalScope.async { // root coroutine with async
                println("Throwing exception from async")
                throw ArithmeticException() // Nothing is printed, relying on user to call await
            }
            try {
                deferred.await()
                println("Unreached")
            } catch (e: ArithmeticException) {
                println("Caught ArithmeticException")
            }
        }
    }
}
