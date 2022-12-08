package com.example.questionnaire.serializable

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.questionnaire.Question
import kotlinx.serialization.KSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encodeToString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule


val json = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
}
fun Decode(jsonimport:String):List<Question>{
    val questions = json.decodeFromString<List<Question>>(jsonimport)
    return questions
}

fun Encode(questions:List<Question>):String{
    val jsonexport = json.encodeToString(questions)
    return jsonexport
}