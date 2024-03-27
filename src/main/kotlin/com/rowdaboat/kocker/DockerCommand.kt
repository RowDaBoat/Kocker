package com.rowdaboat.kocker

import com.rowdaboat.kocker.commands.Build
import com.rowdaboat.kocker.commands.Run
import com.rowdaboat.kocker.commands.ServiceCreate
import com.rowdaboat.kocker.commands.add
import java.io.File

fun docker(builder: DockerCommand.() -> Unit) = Docker(DockerCommand(builder))

class Docker(private val command: DockerCommand) {
	fun get() = command.get()

	fun run() = buildDockerProcess(command.get()).start()

	private fun buildDockerProcess(command: List<String>) =
		ProcessBuilder(*command.toTypedArray())
			.directory(File("."))
			.redirectOutput(ProcessBuilder.Redirect.INHERIT)
			.redirectError(ProcessBuilder.Redirect.INHERIT)!!
}

class DockerCommand(private val dockerCommandCall: DockerCommand.() -> Unit) {
	private val command = mutableListOf("docker")

	fun get(): List<String> {
		dockerCommandCall()
		return command
	}

	fun host(value: String) {
		command.add("--host", value)
	}

	fun run(image: String, containerParameters: Map<String, String>, runCommandCall: Run.() -> Unit) {
		command.addAll(Run(runCommandCall).get())
		command.add(image)
		command.addAll(parametersList(containerParameters))
	}

	fun build(directory: String, buildCommandCall: Build.() -> Unit) {
		command.addAll(Build(buildCommandCall).get())
		command.add(directory)
	}

	fun push(image: String) {
		command.add("push", image)
	}

	fun serviceCreate(
		image: String,
		containerParameters: Map<String, String>,
		serviceCreateCommandCall: ServiceCreate.() -> Unit
	) {
		command.addAll(ServiceCreate(serviceCreateCommandCall).get())
		command.add(image)
		command.addAll(parametersList(containerParameters))
	}

	private fun parametersList(containerParameters: Map<String, String>): List<String> =
		containerParameters
			.map { (key, value) -> listOf("--$key", value) }
			.flatten()
}
