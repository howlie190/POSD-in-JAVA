import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class utTest {

	@Test
	void AtomSymbolValueMatchAtom() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		assertEquals("tom", tom.symbol());
		assertEquals("tom", tom.value());
		assertFalse(tom.match(jerry));
		assertTrue(tom.match(tom));
	}
	@Test
	void NumberSymbolValueMatchNumber() {
		Number number = new Number(3.14), number2 = new Number(2);
		assertEquals("3.14", number.symbol());
		assertEquals("3.14", number.value());
		assertFalse(number.match(number2));
	}
	@Test
	void VariableMatchTesting() {
		Variable X = new Variable("X"), Y = new Variable("Y");
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		X.match(	tom);
		Y.match(X);
		assertEquals("tom", X.value());
		assertEquals("tom", Y.value());
		assertFalse(jerry.match(X));
		assertTrue(X.match(tom));
		assertTrue(Y.match(tom));
	}
	@Test
	void StructTesting() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		Number number = new Number(3.14);
		Variable X = new Variable("X"), Y = new Variable("Y");
		ArrayList<Term>args = new ArrayList<>(Arrays.asList(tom, number, X));
		Struct struct = new Struct(new Atom("s"), args);
		X.match(jerry);
		assertEquals("s(tom, 3.14, X)", struct.symbol());
		assertEquals("s(tom, 3.14, jerry)", struct.value());
		Y.match(struct);
		assertEquals("Y", Y.symbol());
		assertEquals("s(tom, 3.14, jerry)", Y.value());
		assertTrue(Y.match(struct));
		assertTrue(struct.match(Y));
	}
	@Test
	void ListTesting() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		Number number = new Number(3.14);
		Variable X = new Variable("X"), Y = new Variable("Y");
		ArrayList<Term>args = new ArrayList<>(Arrays.asList(tom, number, X));
		ArrayList<Term>args2 = new ArrayList<>(Arrays.asList(tom, number, Y));
		List list = new List(args), list2 = new List(args2);
		assertEquals("[tom, 3.14, X]", list.symbol());
		X.match(jerry);
		assertEquals("[tom, 3.14, jerry]", list.value());
	}
	@Test
	void ListMatchingTesting() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		Number number = new Number(3.14);
		Variable X = new Variable("X"), Y = new Variable("Y");
		ArrayList<Term>args = new ArrayList<>(Arrays.asList(tom, X, number));
		ArrayList<Term>args2 = new ArrayList<>(Arrays.asList(tom, Y, number));
		List list = new List(args), list2 = new List(args2);
		assertTrue(list.match(list2));
		X.match(jerry);
		assertTrue(list2.match(list));
		assertEquals("[tom, jerry, 3.14]", list2.value());
	}
	@Test
	void ListHeadTailTesting() {
		Number number = new Number(1), number2 = new Number(2), number3 = new Number(3);
		ArrayList<Term>args = new ArrayList<>(Arrays.asList(number, number2, number3));
		List list = new List(args);
		assertEquals("1", list.head().symbol());
		assertEquals("[2, 3]", list.tail().symbol());
	}
	@Test
	void SymbolTableTesting() {
		SymbolTable symbolTable = new SymbolTable();
		String symbol = "X";
		Val val = new Val(-1);
		assertFalse(symbolTable.symbolExist(symbol, val));
		symbolTable.getSymbolTable().add(new pair(symbol, 259));
		assertTrue(symbolTable.symbolExist(symbol, val));
		assertEquals(0, val.getVal());
	}
}
