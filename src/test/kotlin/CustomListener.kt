import io.kotest.core.listeners.TestListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult

class CustomListener : TestListener {

    override suspend fun afterContainer(testCase: TestCase, result: TestResult) {
        println("do after container listener")
    }
}
