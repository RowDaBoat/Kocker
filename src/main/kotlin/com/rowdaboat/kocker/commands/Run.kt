package com.rowdaboat.kocker.commands

class Run(private val runCommandCall: Run.() -> Unit) {
	private val command = mutableListOf("run")

	fun name(value :String) {
		command.add("--name", value)
	}

	fun network(value :String) {
		command.add("--network", value)
	}

	fun publish(host: UShort, container: UShort, address: String = "",  protocol: Protocol = Protocol.TCP) {
		command.add("--publish=${bind(address)}$host:$container/${protocol.get()}")
	}

	fun publish(host: PortRange, container: UShort, address: String = "", protocol: Protocol = Protocol.TCP) {
		command.add("--publish=${bind(address)}${host.get()}:$container/${protocol.get()}")
	}

	fun publish(host: PortRange, container: PortRange, address: String = "0.0.0.0", protocol: Protocol = Protocol.TCP) {
		command.add("--publish=${bind(address)}${host.get()}:${container.get()}/${protocol.get()}")
	}

	fun rm() {
		command.add("--rm")
	}

	fun get(): List<String> {
		runCommandCall()
		return command
	}

	private fun bind(address: String) =
		if (address == "") "" else "$address:"
}
