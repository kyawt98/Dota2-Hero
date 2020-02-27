package com.kyawt.dotahero.model

data class Matches(
    val dire_name: String,
    val dire_score: String,
    val dire_team_id: String,
    val duration: String,
    val league_name: String,
    val leagueid: String,
    val match_id: String,
    val radiant_name: String,
    val radiant_score: String,
    val radiant_team_id: String,
    val radiant_win: Boolean,
    val series_id: String,
    val series_type: String,
    val start_time: String
)