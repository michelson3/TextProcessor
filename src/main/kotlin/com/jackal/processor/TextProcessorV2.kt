package org.example.com.jackal.processor

import com.jackal.processor.WordFreq

class TextProcessorV2 {

    fun processText(text: String): List<WordFreq> {
        return text
            .clean()
            .split(" ")
            .getWordCount()
            .mapToList { WordFreq(it.key, it.value) }
            .sortedByDescending  { it.frequency }
    }

    private fun List<String>.getWordCount(): Map<String, Int> {
        val map = hashMapOf<String, Int>()

        for (word in this) {
            if (word == " ") continue
            val trim = word.trim()
            val count = map.getOrDefault(trim, 0)
            map[trim] = count + 1
        }
        return map
    }

    private fun <T> Map<String, Int>.mapToList(transform: (Map.Entry<String, Int>) -> T): MutableList<T> {
        val list = mutableListOf<T>()
        for (entry in this) {
            val freq = transform(entry)
            list.add(freq)
        }
        return list
    }
}

fun String.clean(): String {
    return this.replace("[^A-Za-z]".toRegex(), " ")
        .trim()
}

fun main() {
    val text =
        "In this lesson, we are going to try to connect these points together to write a word frequency statistics program."
    val result = TextProcessorV2().processText(text)
    println(result)
}