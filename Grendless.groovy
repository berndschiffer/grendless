class Grendless {
	
	def filter = new Filter()
	def parser = new Parser()
	def runner
	
	def Grendless(out) {
		runner = new Runner(listener: new GRunListener(out: out))
	}
	
	def run(file) {
		def files = filter.filter(file)
		def testClasses = parser.parse(*files)
		runner.run(*testClasses)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out)

		for(;;) {
			sleep 2000
			runner.run(new File(args[0]))
		}
	}
}

