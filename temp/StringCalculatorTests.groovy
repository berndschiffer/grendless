import org.junit.*

class StringCalculatorTests {
	
	@Test void addsNumber() {
		given(
			'': 0,
			'1': 1,
			'1,2': 3,
			'10': 10,
		).makeSureThat stringsAddToNumber
	}
	
	@Test void complainsAboutNegativeNumbers() {
		given(
			'-1': 'no negatives allowed: [-1]',
			'-1,-2': 'no negatives allowed: [-1, -2]',
			'-10': 'no negatives allowed: [-10]',
			'-1,1': 'no negatives allowed: [-1]',
		).makeSureThat{ string, message -> assertAdditionFails(string, message) }
	}
	
	@Test void useDifferentDelimiters() {
		given(
			'1\n2': 3,
			'\\;\n1;2': 3,
			'\\;\n1;;2': 3,
		).makeSureThat stringsAddToNumber
	}
	
	def stringsAddToNumber = { string, numbers -> assert numbers == add(string) }

	def assertAdditionFails(string, message) {
		try{
			add(string)
			assert false
		}
		catch(IllegalArgumentException exception){
			assert message == exception.message
		}	
	}	

	def given(parameters) {
		[makeSureThat: { assertion -> parameters.each assertion }]
	}
	
	def add(string) {
		if(!string) return 0
		def numbers = string.findAll(/-?\d+/)*.toInteger()
		checkNegatives(numbers)
		numbers.sum()
	}
	
	def checkNegatives(numbers) {
		def negatives = numbers.findAll{ it < 0 }
		if(negatives)
			throw new IllegalArgumentException("no negatives allowed: $negatives")
	}
}