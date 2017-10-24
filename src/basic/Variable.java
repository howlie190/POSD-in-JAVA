package basic;

import java.util.ArrayList;

import structure.List;
import structure.Struct;

public class Variable implements Term {
	private String _symbol, _value;
	private boolean _assignable = true, _result, _structMatching = false, _listMatching = false;
	private Struct _struct;
	private List _list;
	private ArrayList<Variable>_link = new ArrayList<>();
	public Variable(String symbol) { _symbol = _value = symbol; }
	public String symbol() { return _symbol; }
	public String value() {
		if(_structMatching)
			return _struct.value();
		if(_listMatching)
			return _list.value();
		return _value;
	}
	public Variable getVariable() { return this; }
	public boolean match(Term term) {
		Variable pVariable = term.getVariable();
		Struct pStruct = term.getStruct();
		List pList = term.getList();
		if(pVariable != null) {
			_result = false;
			if(_value == pVariable.value()) {
				_result = true;
			} else if(_assignable || pVariable._assignable) {
				_value = pVariable.symbol();
				dataCopy(pVariable);
				if(!_assignable)
					chain();
				else if(!(pVariable._assignable))
					pVariable.chain();
				_result = true;
			}
			return _result;
		}
		if(pStruct != null) {
			_result = false;
			if(!_structMatching || _struct.value() == pStruct.value()) {
				_struct = pStruct;
				_structMatching = true;
				_result = true;
			}
			return _result;
		}
		if(pList != null) {
			_result = false;
			if(!_listMatching || _list.value() == pList.value()) {
				_list = pList;
				_listMatching = false;
				_result = true;
			}
			return _result;
		}
		return basicMatch(term);
	}
	private void dataCopy(Variable pVariable) {
		ArrayList<Variable>_temp = new ArrayList<>(_link);
		for(int i = 0; i < pVariable._link.size(); i++)
			_link.add(pVariable._link.get(i));
		for(int i = 0; i < _temp.size(); i++) {
			pVariable._link.add(_temp.get(i));
			_link.get(i)._link.add(pVariable);
		}
		_link.add(pVariable);
		pVariable._link.add(this);
		
	}
	private void chain() {
		for(int i = 0; i < _link.size(); i++) {
			_link.get(i)._value = _value;
			_link.get(i)._assignable = false;
		}
	}
	private boolean basicMatch(Term term) {
		_result = _assignable;
		if(_result || _value == term.value()) {
			_value = term.value();
			_assignable = false;
			_result = true;
			chain();
		}
		return _result;
	}
}
