import org.junit.*

class CacheTest {
	
	def cache = new Cache()

	def files = [[absolutePath: '/path/to/file.groovy', lastModified: { 0 }],
							 [absolutePath: '/path/to/another.groovy', lastModified: { 1 }]]
	
	@Test void executesWhenEmpty() {
		assert cache.execute { true }.ifThereAreNew(files)
	}
	
	@Test void doesntExecuteWhenFilesAreNotModified() {
		cache.execute {}.ifThereAreNew(files)
		assert !cache.execute { true }.ifThereAreNew(files)
	}
	
	@Test void executesWhenFilesAreModified() {
		cache.execute {}.ifThereAreNew(files)
		files.last().lastModified = { 2 }
		assert cache.execute { true }.ifThereAreNew(files)
	}
	
	@Test void executesActionWhenFileWasDeleted() {
		cache.execute {}.ifThereAreNew(files)
		files.pop()
		assert cache.execute { true }.ifThereAreNew(files)		
	}
}