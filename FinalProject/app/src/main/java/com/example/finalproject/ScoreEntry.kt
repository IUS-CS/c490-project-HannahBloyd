package com.example.finalproject

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ScoreEntry(
    //var id: UUID = UUID.randomUUID(),
    var name: String = "",
    var score: Int = 0
)