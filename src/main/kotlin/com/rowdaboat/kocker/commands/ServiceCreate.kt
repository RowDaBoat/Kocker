package com.rowdaboat.kocker.commands

class ServiceCreate(private val runCommandCall: ServiceCreate.() -> Unit) {
	private val command = mutableListOf("service create")

	fun name(value :String) {
		command.add("--name", value)
	}

	fun replicas(count :Int) {
		command.add("--replicas", count.toString())
	}

	fun network(value :String) {
		command.add("--network", value)
	}

	fun get(): List<String> {
		runCommandCall()
		return command
	}
}
