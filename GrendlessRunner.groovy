class GrendlessRunner {
	
	def formatter
	
	def GrendlessRunner(out) {
		formatter = new TestResultFormatter(out: out)
	}
	
	def run(... tests) {
		def testClass = new GroovyClassLoader().parseClass(tests[0])
		def core = new org.junit.runner.JUnitCore()
		def result = core.run(testClass)
		formatter.format(result)
	}
}