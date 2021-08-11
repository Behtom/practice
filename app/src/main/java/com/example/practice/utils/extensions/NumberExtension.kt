package com.example.practice.utils.extensions

import com.example.practice.utils.tools.Tools.localLoc

fun Double.toMoneyFormat(): String {
    return String.format(localLoc, "$%,.2f", this)
}