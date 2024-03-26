package com.cignis.kocker.commands

class ServiceCreate(private val runCommandCall: ServiceCreate.() -> Unit) {
	private val command = mutableListOf("service create")

	fun name(value :String) { command.add("--name $value") }

	fun replicas(count :Int) { command.add("--replicas $count")}

	fun network(value :String) { command.add("--network $value")}

	fun get(): String {
		runCommandCall()
		return command.joinToString(" ")
	}
}
