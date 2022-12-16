package com.example.formulaone.data.model.firebase_test

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class ForTestFireBase(
    val username: String? = null,
    val email: String? = null
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "username" to username,
            "email" to email
        )
    }
}
