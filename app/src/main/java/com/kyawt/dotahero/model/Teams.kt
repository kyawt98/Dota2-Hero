package com.kyawt.dotahero.model

data class Teams(
    val last_match_time: String,
    val logo_url: String,
    val losses: String,
    val name: String,
    val rating: Double,
    val tag: String,
    val team_id: String,
    val wins: Int
)