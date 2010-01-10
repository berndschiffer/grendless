import org.junit.*

class CollectorTest {
	
	def collector = new Collector()
	def file = [name: 'file.groovy', file: true]
	
	@Test void returnsSingleFile() {
		assert [file] == collector.collect(file)
	}
	
	@Test void collectsFilesRecursivly() {
		def dir = makeFakeDir(file)
		assert [file] == collector.collect(dir)
	}
	
	def makeFakeDir(file) {
		[eachFileRecurse: { closure -> closure(file) } ]
	}
}