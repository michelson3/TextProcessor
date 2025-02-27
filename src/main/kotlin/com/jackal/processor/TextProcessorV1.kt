package com.jackal.processor

import java.io.File

class TextProcessorV1 {
    fun processFile(file: File): List<WordFreq> {
        val text = file.readText(Charsets.UTF_8)
        return processText(text)
    }

    fun processText(text: String): List<WordFreq> {
        // 文本清洗,符号替换为空格
        val cleaned = clean(text)
        // 文本分割
        val words = cleaned.split(" ")
        // 统计单词频率
        val map = getWordCount(words)
        // 词频排序
        val list = sortByFrequency(map)

        return list
    }

    fun clean(text: String): String {
        return text.replace("[^A-Za-z]".toRegex(), " ")
            .trim()
    }

    fun getWordCount(list: List<String>): Map<String, Int> {
        val map = hashMapOf<String, Int>()

        for (word in list) {
            if (word == " ") continue
            val trim = word.trim()
            val count = map.getOrDefault(trim, 0)
            map[trim] = count + 1
        }
        return map
    }

    fun sortByFrequency(map: Map<String, Int>): MutableList<WordFreq> {
        val list = mutableListOf<WordFreq>()
        for (entry in map) {
            if (entry.key == "") continue
            val freq = WordFreq(entry.key, entry.value)
            list.add(freq)
        }
        list.sortByDescending {
            it.frequency
        }
        return list
    }
}

data class WordFreq(val word: String, val frequency: Int)

fun main() {
    val text =
        "In this lesson, we are going to try to connect these points together to write a word frequency statistics program."
    val result = TextProcessorV1().processText(text)
    println(result)
}