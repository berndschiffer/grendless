class Grendless {
	
	def cache = new Cache()
	def filter = new Filter()
	def collector = new Collector()
	def parser
	def runner
	
	def Grendless(out) {
		def listener = new GRunListener(out: out)
		runner = new Runner(listener: listener)
		parser = new Parser(listener: listener)
	}
	
	def run(file) {
		def files = collector.collect(file)
		cache.execute {
			def filteredFiles = filter.filter(files)
			def testClasses = parser.parse(*filteredFiles)
			runner.run(*testClasses)
		}.ifThereAreNew(files)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out)

		for(;;) {
			sleep 200
			runner.run(new File(args[0]))
		}
	}
}

