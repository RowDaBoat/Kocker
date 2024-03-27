package com.rowdaboat.kocker.commands

enum class Protocol { TCP, UDP, SCTP }

fun Protocol.get() = when(this) {
    Protocol.TCP -> "tcp"
    Protocol.UDP -> "udp"
    Protocol.SCTP -> "sctp"
}
