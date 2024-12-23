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
        })
  )