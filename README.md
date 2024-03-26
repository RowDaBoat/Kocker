# Kocker: Docker in Kotlin

[![Test and Publish](https://github.com/RowDaBoat/kocker/actions/workflows/ci.yml/badge.svg)](https://github.com/RowDaBoat/kocker/actions/workflows/ci.yml)

**Kocker** is a simple DSL for docker written in Kotlin. It has no weird integrations or requirements, it just builds a command line and runs it using `ProcessBuilder`. Its only dependency is, naturally, having docker installed on the system where it is run.

## Usage

```kotlin
docker {
    run { name("ubuntu") }
}.run()
```
