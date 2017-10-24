package basic;

import java.text.DecimalFormat;

public class Number implements Term {
	private double _value;
	public Number(double value) { _value = value; }
	public String symbol() {
		DecimalFormat format = new DecimalFormat("0.######");
		return format.format(_value);
	}
	public String value() { return symbol(); }
	public boolean match(Term term) {
		Variable pVariable = term.getVariable();
		if(pVariable != null)
			return pVariable.match(this);
		return value() == term.value();
	}
}
