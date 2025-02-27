package com.jackal.processor

import kotlin.test.Test
import kotlin.test.assertEquals

class TestProcessorV1 {

    @Test
    fun testProcessText() {
        val text =
            "In this lesson, we are going to try to connect these points together to write a word frequency statistics program."
        val processor = TextProcessorV1()
        val result = processor.processText(text)
        assertEquals(3, result[0].frequency)
        assertEquals("to", result[0].word)
    }
}