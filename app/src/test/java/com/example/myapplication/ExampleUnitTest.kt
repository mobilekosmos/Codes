package com.example.myapplication

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test
import java.text.BreakIterator
import java.util.*

class ExampleUnitTest {

    @Test
    fun getAmountOfWords() {
        runTestsWith(getAmountOfWords)
        runTestsWith(::getAmountOfWords2)
        runChineseTest()
    }

    private fun runChineseTest() {
        val iterator: BreakIterator = BreakIterator.getWordInstance(Locale.CHINESE)
        val sentence = "你好，我是大卫"
        iterator.setText(sentence)
        val words: MutableList<String> = ArrayList()
        var start = iterator.first()
        var end = iterator.next()
        while (end != BreakIterator.DONE) {
            words.add(sentence.substring(start, end))
            start = end
            end = iterator.next()
        }
        var yourString = words.toString()
        yourString = yourString.replace("\\s+".toRegex(), "") //remove white space
        yourString = yourString.replace("\"\"\"(?U)\\p{Punct}+\"\"\"".toRegex(), "") //removes all punctuation
        println(yourString)
        //assertThat(words.size, equalTo(6))
    }

    private fun runTestsWith(countAlg: (String) -> Int) {
        val wordsAmount = 3
        var amount = countAlg("Hello, I'm David")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg("Hello,  I'm David")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg("Hello, I'm  David")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg("Hello,  I'm  David")
        assertThat(amount, equalTo(wordsAmount))

        amount = countAlg("Hello,  I'm David ")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg("Hello, I'm  David ")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg("Hello,  I'm  David ")
        assertThat(amount, equalTo(wordsAmount))

        amount = countAlg(" Hello,  I'm David")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg(" Hello, I'm  David")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg(" Hello,  I'm  David")
        assertThat(amount, equalTo(wordsAmount))

        amount = countAlg(" Hello,  I'm David ")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg(" Hello, I'm  David ")
        assertThat(amount, equalTo(wordsAmount))
        amount = countAlg(" Hello,  I'm  David ")
        assertThat(amount, equalTo(wordsAmount))

        amount = countAlg("")
        assertThat(amount, equalTo(0))

        amount = countAlg(" ")
        assertThat(amount, equalTo(0))

        amount = countAlg("  ")
        assertThat(amount, equalTo(0))

        amount = countAlg("      ")
        assertThat(amount, equalTo(0))
    }

    private var getAmountOfWords = fun(text: String) : Int{
        if (text.isBlank()) {
            return 0
        }
        // TODO; won't work with asian languages as they don't use spaces as word delimiter.
        val textSplitted = text.split(" ").filter { it.isNotEmpty() }
        return textSplitted.toString().split(" ").size
    }

    private fun getAmountOfWords2(text : String) : Int {
        // TODO: also, doesn't work with asian languages
        val tokens = StringTokenizer(text)
        return tokens.countTokens()
    }
}
