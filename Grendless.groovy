class Grendless {
	
	def cache = new Cache()
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
		cache.execute {
			def testClasses = parser.parse(*files)
			runner.run(*testClasses)
		}.ifThereAreNew(files)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out)

		for(;;) {
			sleep 2000
			runner.run(new File(args[0]))
		}
	}
}

