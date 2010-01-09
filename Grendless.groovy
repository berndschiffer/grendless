class Grendless {
	
	def formatter
	
	def Grendless(out) {
		formatter = new TestResultFormatter(out: out)
	}
	
	def run(... tests) {
		def testClasses = new Parser().parse(tests)
		def core = new org.junit.runner.JUnitCore()
		def result = core.run(*testClasses)
		formatter.format(result)
	}
	
	def start(file) {
		def files = new Filter().filter(file)
		run(*files)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out)

		for(;;) {
			sleep 2000
			runner.start(new File(args[0]))
		}
	}
}

