class Grendless {
	
	def filter = new Filter()
	def parser = new Parser()
	def runner
	def formatter
	
	def Grendless(out) {
		runner = new Runner(listener: new GRunListener(out: out))
		formatter = new GFormatter(out: out)
	}
	
	def run(file) {
		def files = filter.filter(file)
		def testClasses = parser.parse(*files)
		def result = runner.run(*testClasses)
		formatter.format(result)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out)

		for(;;) {
			sleep 2000
			runner.run(new File(args[0]))
		}
	}
}

