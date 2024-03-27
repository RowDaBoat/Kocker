package com.rowdaboat.kocker.commands

class Build(private val buildCommandCall: Build.() -> Unit) {
	private val command = mutableListOf("build")

	fun buildArg(argument: String, value: String) {
		command.add("--build-arg", "$argument=$value")
	}

	fun label(name: String, value: String) {
		command.add("--label", "$name=$value")
	}

	fun tag(tag: String) {
		command.add("--tag", tag)
	}

	fun get(): List<String> {
		buildCommandCall()
		return command
	}
}
