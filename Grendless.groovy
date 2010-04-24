class Grendless {
	
	def cache = new Cache()
	def parser 
	def runner
	
	def Grendless(out, afreshLines) {
		def screen = new Screen(afreshLines: afreshLines,
														delayer: new Delayer(lag: 7),
														out: out)
		def listener = new GRunListener(screen: screen)
		runner = new Runner(listener: listener)
		parser = new Parser(listener: listener)
	}
	
	def run(file) {
		def files = new Collector().collect(file)
		cache.execute {
			def filteredFiles = new Filter().filter(files)
			def testClasses = parser.parse(*filteredFiles)
			runner.run(*testClasses)
		}.ifThereAreNew(files)
	}
	
	static void main(args) {
		def runner = new Grendless(System.out, 40)

		for(;;) {
			sleep 200
			runner.run(new File(args[0]))
		}
	}
}

