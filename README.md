Issue raised at sbt/sbt#7971.

# Description
Sample project to illustrate an issue with log levels in commands. This project defines an `sbt` command `logThenFail` which makes 3 log entries using the logger `state.log` then throws a `MessageOnlyException` to fail the command.

# Problem
Log entries created from a command does not seem to display correctly in `warn` or `error` levels.
* Logging made from `state.log` does not show up in any `warn` and `error` log levels.
* `error` log from throwing `MessageOnlyException` also does not show up in `warn` and `error` log levels.

This is confusing to users, especially in the case of throwing `MessageOnlyException` to fail the command and the build.

```log
$ sbt logThenFail
[info] [launcher] getting org.scala-sbt sbt 1.10.9  (this may take some time)...
[info] welcome to sbt 1.10.9 (Eclipse Adoptium Java 17.0.13)
[info] loading project definition from redacted\sbt-log-level\project
[info] loading settings for project log-level from build.sbt...
[info] set current project to log-level (in build file:/redacted/github/sbt-log-level/)
[info] Some info
[warn] Some warnings
[error] Some errors
[error] Terminating error
```

```log
# expect the warnings and errors to show up but empty
$ sbt --warn logThenFail

```

```log
# expect the errors to show up but empty
$ sbt --error logThenFail

```
