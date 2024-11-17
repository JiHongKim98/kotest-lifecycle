# kotest-lifecycle

## Base test code

```kotlin
class LifeCycleTest : BehaviorSpec({

    listeners(CustomListener())

    isolationMode = IsolationMode.SingleInstance

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

```

## SingleInstance (DEFAULT)

```
| >> given1 when1 then1 || 
| >> given1 when1 then1 || then2 || 
do after container listener
| >> given1 when1 then1 || then2 || when2 then3 || 
| >> given1 when1 then1 || then2 || when2 then3 || then4 || 
do after container listener
do after container listener
```

테스트 클래스에 대해 하나의 인스턴스만을 생성하여, `Given`, `When`, `Then` 블록에서 모두 동일한 인스턴스를 공유

## InstancePerLeaf

```
| >> given1 when1 then1 || 
do after container listener
do after container listener
| >> given1 when1 then2 || 
do after container listener
do after container listener
| >> given1 when2 then3 || 
do after container listener
do after container listener
| >> given1 when2 then4 || 
do after container listener
do after container listener
```

각 `it` 블록(`Then` 블록)마다 새로운 인스턴스를 생성하여, 테스트 간 상태를 독립적으로 유지

단, `Given`과 `When` 블록은 같은 인스턴스를 공유하지만, `Then` 블록에 도달할 때마다 새 인스턴스를 생성하여 실행한다.

## InstancePerTest

```
| >> given1 when1 then1 || 
do after container listener
do after container listener
| >> given1 when1 then2 || 
do after container listener
do after container listener
do after container listener
do after container listener
| >> given1 when2 then3 || 
do after container listener
do after container listener
| >> given1 when2 then4 || 
do after container listener
do after container listener
do after container listener
do after container listener
do after container listener
```

각 `Then` 블록에서 완전히 새로운 인스턴스를 생성하며, 모든 `Given`, `When`, `Then` 블록이 서로 상태를 공유하지 않는다.

즉, `Given`과 `When` 단계의 상태 또한 `Then`으로 전달되지 않는다
