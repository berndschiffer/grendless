import org.junit.*

class FilterTest {
	
	def filter = new Filter()
	
	@Test void filtersTestFiles() {
		def files = [
			[name: 'Test.groovy'],
			[name: 'Tests.groovy'],
			[name: 'AnythingElse.']
		]
		assert files[0..1] == filter.filter(files)
	}
}