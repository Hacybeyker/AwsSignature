package com.hacybeyker.awssignature.repository.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Carlos Osorio on 11/03/2021
 */

fun generateDate(): String {
    val now = Date()
    val sdf = SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.getDefault())
    val utc = TimeZone.getTimeZone("UTC")
    sdf.timeZone = utc
    return sdf.format(now).toString()
}