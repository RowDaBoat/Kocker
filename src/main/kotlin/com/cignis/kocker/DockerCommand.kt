package com.cignis.kocker

import com.cignis.kocker.commands.Build
import com.cignis.kocker.commands.Run
import com.cignis.kocker.commands.ServiceCreate
import java.io.File

fun docker(builder: DockerCommand.() -> Unit) = Docker(DockerCommand(builder))

class Docker(private val command: DockerCommand) {
	fun get() = command.get().joinToString(" ")

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

	fun host(value: String) { command.add("--host $value") }

	fun run(image: String, containerParameters: Map<String, String>, runCommandCall: Run.() -> Unit) =
		command.add("${Run(runCommandCall).get()} $image ${parametersToString(containerParameters)}")

	fun build(directory: String, buildCommandCall: Build.() -> Unit) =
		command.add("${Build(buildCommandCall).get()} $directory")

	fun push(image: String) = command.add("push $image")

	fun serviceCreate(
		image: String,
		containerParameters: Map<String, String>,
		serviceCreateCommandCall: ServiceCreate.() -> Unit
	) = command.add("${ServiceCreate(serviceCreateCommandCall).get()} $image ${parametersToString(containerParameters)}")

	private fun parametersToString(containerParameters: Map<String, String>): String =
		containerParameters.entries.joinToString(" ") { "--${it.key} ${it.value}" }
}
