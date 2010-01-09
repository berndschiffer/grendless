class Grendless {
	
	def filter = new Filter()
	def parser
	def runner
	
	def Grendless(out) {
		def listener = new GRunListener(out: out)
		runner = new Runner(listener: listener)
		parser = new Parser(listener: listener)
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

