class Runner {
	
	def run(... tests) {
		def testClasses = new Parser().parse(tests)
		def core = new org.junit.runner.JUnitCore()
		def result = core.run(*testClasses)
	}
}