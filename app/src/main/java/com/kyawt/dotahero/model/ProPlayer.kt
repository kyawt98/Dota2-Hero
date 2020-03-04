package com.kyawt.dotahero.model

data class ProPlayer(
    val account_id: String,
    val avatar: String,
    val avatarfull: String,
    val avatarmedium: String,
    val cheese: String,
    val country_code: String,
    val fantasy_role: String,
    val fh_unavailable: Boolean,
    val full_history_time: String,
    val is_locked: Boolean,
    val is_pro: Boolean,
    val last_login: String,
    val last_match_time: String,
    val loccountrycode: String,
    val locked_until: Any,
    val name: String,
    val personaname: String,
    val plus: Boolean,
    val profileurl: String,
    val steamid: String,
    val team_id: String,
    val team_name: String,
    val team_tag: String
)