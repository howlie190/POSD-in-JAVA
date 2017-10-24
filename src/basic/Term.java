package basic;

import structure.List;
import structure.Struct;

public interface Term {
	public abstract String symbol();
	public default String value() { return symbol(); }
	public default boolean match(Term term) { return value() == term.value(); }
	public default Variable getVariable() { return null; }
	public default Struct getStruct() { return null; }
	public default List getList() { return null; }
}
