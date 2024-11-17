import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.BehaviorSpec

class LifeCycleTest : BehaviorSpec({

    listeners(CustomListener())

    isolationMode = IsolationMode.InstancePerTest

    Given("given 1") {
        var message = "| >> given1 "
        When("when 1") {
            message += "when1 "
            Then("then 1") {
                message += "then1 || "
                println(message)
            }
            Then("then 2") {
                message += "then2 || "
                println(message)
            }
        }
        When("when 2") {
            message += "when2 "
            Then("then 3") {
                message += "then3 || "
                println(message)
            }
            Then("then 4") {
                message += "then4 || "
                println(message)
            }
        }
    }
})
