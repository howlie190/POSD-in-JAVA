package basic;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import structure.List;
import structure.Struct;

class utTest {

	@Test
	public void atomMatchAtom() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		assertTrue(tom.match(tom));
		assertFalse(jerry.match(tom));
	}
	@Test
	public void variableMatchAtom() {
		Atom tom = new Atom("tom");
		Variable X = new Variable("X");
		assertEquals("X", X.value());
		assertTrue(X.match(tom));
		assertEquals("tom", X.value());
	}
	@Test
	public void variableMatchVariable() {
		Variable X = new Variable("X"), Y = new Variable("Y"), Z = new Variable("Z");
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		assertTrue(X.match(Y));
		assertTrue(X.match(Z));
		assertTrue(Z.match(tom));
		assertEquals("tom", X.value());
		assertEquals("tom", Y.value());
		assertEquals("tom", Z.value());
		assertFalse(jerry.match(Y));
	}
	@Test
	public void variableMatchNumber() {
		Variable X = new Variable("X"), Y = new Variable("Y"), Z = new Variable("Z");
		Number num1 = new Number(3.14), num2 = new Number(1);
		assertTrue(X.match(Y));
		assertTrue(X.match(Z));
		assertTrue(Z.match(num1));
		assertEquals("3.14", X.value());
		assertEquals("3.14", Y.value());
		assertEquals("3.14", Z.value());
		assertFalse(num2.match(Y));
	}
	@Test
	public void variableMatchStruct() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		Number number = new Number(1);
		Variable X = new Variable("X");
		ArrayList<Term>v = new ArrayList<>(Arrays.asList(tom, number, X));
		Struct s = new Struct(new Atom("s"), v);
		assertEquals("s(tom, 1, X)", s.symbol());
		jerry.match(X);
		assertEquals("s(tom, 1, jerry)", s.value());
	}
	@Test
	public void listMatchingList() {
		Atom tom = new Atom("tom"), jerry = new Atom("jerry");
		Number number = new Number(1);
		Variable X = new Variable("X");
		ArrayList<Term>v = new ArrayList<>(Arrays.asList(tom, number, X));
		List l = new List(v);
		List l2 = new List(v);
		X.match(jerry);
		assertEquals("[tom, 1, X]", l.symbol());
		assertEquals("[tom, 1, jerry]", l.value());
		assertTrue(l.match(l2));
	}
}
