import java.util.ArrayList;

public class Struct extends Term {
	private Atom _name;
	private ArrayList<Term>_args;
	public Struct(Atom name, ArrayList<Term>args) {
		_name = name;
		_args = args;
	}
	public String symbol() {
		if(_args.isEmpty())
			return _name.symbol() + "()";
		String result = _name.symbol() + "(";
		for(int i = 0; i < _args.size() - 1; i++)
			result += (_args.get(i).symbol() + ", ");
		return result += (_args.get(_args.size() - 1).symbol() + ")");
	}
	public String value() {
		if(_args.isEmpty())
			return _name.symbol() + "()";
		String result = _name.symbol() + "(";
		for(int i = 0; i < _args.size() - 1; i++)
			result += (_args.get(i).value() + ", ");
		return result += (_args.get(_args.size() - 1).value() + ")");
	}
}
