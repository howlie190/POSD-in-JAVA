package structure;

import java.util.ArrayList;

import basic.Atom;
import basic.Term;
import basic.Variable;

public class Struct implements Term {
	private Atom _name;
	private ArrayList<Term>_args = new ArrayList<>();
	private boolean _result = true;
	public Struct(Atom name, ArrayList<Term>args) {
		_name = name;
		_args = args;
	}
	public String symbol() {
		String _result = _name.symbol() + "(";
		for(int i = 0; i < _args.size() - 1; i++)
			_result += (_args.get(i).symbol() + ", ");
		return _result += (_args.get(_args.size() - 1).symbol() + ")");
	}
	public String value() {
		String _result = _name.symbol() + "(";
		for(int i = 0; i < _args.size() - 1; i++)
			_result += (_args.get(i).value() + ", ");
		return _result += (_args.get(_args.size() - 1).value() + ")");
	}
	public boolean match(Term term) {
		Struct pStruct = term.getStruct();
		Variable pVariable = term.getVariable();
		if(pStruct != null) {
			if((!_name.match(pStruct._name)) && (_args.size() != pStruct._args.size()))
				_result = false;
			else
				for(int i = 0; i < _args.size(); i++)
					if(_args.get(i).symbol() != pStruct._args.get(i).symbol()) {
						_result = false;
						break;
					}
			return _result;
		}
		if(pVariable != null)
			return pVariable.match(this);
		return false;
	}
}
