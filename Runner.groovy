class Runner {
	
	def run(... testClasses) {
		def core = new org.junit.runner.JUnitCore()
		def result = core.run(*testClasses)
	}
}