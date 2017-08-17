package org.spekframework.jvm

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import org.jetbrains.spek.api.Spek
import org.junit.jupiter.api.Test
import org.spekframework.jvm.support.AbstractSpekJvmRuntimeTest

/**
 * @author Ranie Jade Ramiso
 */
class AfterGroupTest: AbstractSpekJvmRuntimeTest() {
    @Test
    fun testAfterGroup() {
        class TestSpek: Spek({
            var counter = 0
            group("nested group") {
                afterGroup { counter++ }

                test("should be 0") {
                    assertThat(counter, equalTo(0))
                }
            }

            group("another nested group") {
                test("should be 1") {
                    assertThat(counter, equalTo(1))
                }
            }
        })

        val recorder = executeTestsForClass(TestSpek::class)

        assertThat(recorder.testSuccessfulCount, equalTo(2))
    }
}