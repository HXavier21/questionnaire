package com.example.questionnaire

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
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
    var selectedItem: Int = -1
) : Question() {
    @Transient
    val selected: MutableState<Int> = mutableStateOf(-1)
}

@Serializable
data class MultipleChoice(
    override val headline: String,
    val options: List<String>,
    val checkedItems: MutableList<Int> = mutableListOf()
) : Question() {
    @Transient
    val checked: SnapshotStateList<Int> = mutableStateListOf()
}

@Serializable
data class BlankFill(
    override val headline: String,
    var answer: String = ""
) : Question() {
    @Transient
    val text: MutableState<String> = mutableStateOf("")
}

val options: List<String> = listOf(
    "Apple",
    "Cherry",
    "Pear",
    "Watermelon",
    "Banana",
    "Strawberry",
    "Orange"
)
val question1: SingleChoice = SingleChoice(
    "Favourite fruit:",
    options
)
val question2: MultipleChoice = MultipleChoice(
    "Fruit juice you like:",
    options
)
val question3: BlankFill = BlankFill(
    "Briefly talk about why you like them."
)
val obj: List<Question> = listOf(question1, question2, question3)