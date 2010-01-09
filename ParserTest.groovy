import org.junit.*

class ParserTest {
	
	def parser = new Parser()
	
	@Test void parsesSingleClass() {
		assert Class == parser.parse('class A{}')[0].getClass()
	}
	
	@Test void parsesSeveralClasses() {
		assert 2 == parser.parse('class A{}', 'class B{}').size()
	}
}