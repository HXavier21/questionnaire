package com.example.questionnaire

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val questions: MutableList<Question>
            MyAppNavHost()
        }
    }
}

val total = 3

enum class QuestionType {
    Single_Choice, Multiple_Choice, Blank_Fill
}

@Serializable
sealed class Ques
data class SingleChoice(val headline: String,val options: List<String>):Ques()
data class MutipleChoice(val headline: String,val options: List<String>):Ques()
data class BlankFill(val headline: String,val text:String):Ques()

data class Question(
    val type: QuestionType, val headline: String, val options: List<String>
)

val options: List<String> = listOf(
    "aaadhashuiashdfuaihsdfiuadhfhdfdsfa",
    "bbbbsahidshfsaidhsidsdhsdsdsd",
    "ccccccccccccccccccccccccccccccccccccccccccc",
    "ddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"
)
val question1: Question = Question(
    QuestionType.Single_Choice, "aaaaaaaasdasdawdwddds,why?", options
)
val question2: Question = Question(
    QuestionType.Multiple_Choice, "adjnfuahrfiuewhioncsanaasdgfsg,what?", options
)
val question3: Question = Question(
    QuestionType.Blank_Fill, "nsfjdkhfafohiwhfioaaifsis,how?", options
)
