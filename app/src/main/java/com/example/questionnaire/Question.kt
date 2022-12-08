package com.example.questionnaire

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

private const val TAG = "Question"

@Serializable
sealed class Question {
    abstract val headline: String
}

@Serializable
data class SingleChoice(
    override val headline: String,
    val options: List<String>,
    @Transient
    val selected: MutableState<Int> = mutableStateOf(-1)
) : Question() {
    val selectedItem: Int = selected.value
}

@Serializable
data class MultipleChoice(
    override val headline: String,
    val options: List<String>,
    val checkedItems: List<Int> = emptyList()
) : Question() {
    @Transient
    val checked: SnapshotStateList<Int> = mutableStateListOf<Int>().apply {
        Log.d(TAG, "Init checked state")
        addAll(checkedItems)
    }
}

@Serializable
data class BlankFill(
    override val headline: String,
    @Transient
    val text: MutableState<String> = mutableStateOf("")
) : Question() {
    val answer: String = text.value
}

val options: List<String> = listOf(
    "Apple",
    "Cherry",
    "Pear",
    "Pineapple"
)
val question1: SingleChoice = SingleChoice(
    "Favourite fruit?", options
)
val question2: MultipleChoice = MultipleChoice(
    headline = "Fruit juice",
    options = options,
    checkedItems = listOf(1, 2, 3)
)
val question3: BlankFill = BlankFill(
    "Blank", mutableStateOf("")
)
val obj: List<Question> = listOf(question1, question2, question3)