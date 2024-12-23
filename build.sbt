val logThenFailTask = TaskKey[File](
      "log-then-fail-task",
      "Make an info, warning and error log then throws an exception"
    )

lazy val `log-level` = (project in file("."))
  .settings(
    commands += Command.command(
        "logThenFail",
        "Make some loggings then fail the command",
        "Make some loggings then fail the command"
      )(
        state => {
          state.log.info("Some info")
          state.log.warn("Some warnings")
          state.log.error("Some errors")
          throw new MessageOnlyException("Terminating error")
        }
      ),
    logThenFailTask := Def.task {
      streams.value.log.info("Some info")
      streams.value.log.warn("Some warnings")
      streams.value.log.error("Some errors")
      throw new MessageOnlyException("Terminating error")
    }.value
  )