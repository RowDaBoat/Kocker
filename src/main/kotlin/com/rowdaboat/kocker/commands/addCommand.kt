package com.rowdaboat.kocker.commands

fun MutableList<String>.add(vararg command: String) {
    this.addAll(command)
}
