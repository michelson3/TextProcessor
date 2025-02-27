package com.jackal.processor

import org.example.com.jackal.processor.TextProcessorV3
import kotlin.test.Test
import kotlin.test.assertEquals

class TestProcessorV3 {

    @Test
    fun testProcessText() {
        val text =
            "In this lesson, we are going to try to connect these points together to write a word frequency statistics program."
        val processor = TextProcessorV3()
        val result = processor.processText(text)
        assertEquals(3, result[0].frequency)
        assertEquals("to", result[0].word)
    }
}