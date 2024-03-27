package com.rowdaboat.kocker.commands

fun range(from: UShort, to: UShort) = PortRange(from, to)

class PortRange(private val from: UShort, private val to: UShort) {
    fun get() = "$from-$to"
}
