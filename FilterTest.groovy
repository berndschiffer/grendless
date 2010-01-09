import org.junit.*

class FilterTest {
	
	def filter = new Filter()
	
	@Test void returnsSingleFile() {
		def singleFile = [file: true]
		assert [singleFile] == filter.filter(singleFile)
	}
	
	@Test void filtersTestFiles() {
		assertFiltersFile([name: 'Test.groovy'])
		assertFiltersFile([name: 'Tests.groovy'])
	}
	
	@Test void blocksNonTestFiles() {
		assertDoesntFilterFile([name: 'AnythingElse.'])
	}
	
	def assertDoesntFilterFile(file) {
		def dir = makeFakeDir(file)
		assert !filter.filter(dir)
	}
	
	def assertFiltersFile(file) {
		def dir = makeFakeDir(file)
		assert [file] == filter.filter(dir)
	}
	
	def makeFakeDir(file) {
		[eachFileRecurse: { closure -> closure(file) } ]
	}
}