// huge thanks to @lolgab onn the Scala discord!

println(">>>> Running buildCompilationSettings.sc")
println(">>>>>> Setting -Ytasty-reader in the ammonite interpreter's compiler config!")
println(">>>>>>>> Eventually soon hopefully this won't be necessary!")
interp.configureCompiler { c =>
  val settings = c.settings
  settings.YtastyReader.value = true
}


