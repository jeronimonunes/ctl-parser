package br.ufmg.dcc.nanocomp.ctl.model.functions;

import java.util.List;

import br.ufmg.dcc.nanocomp.ctl.model.BasicFunction;
import br.ufmg.dcc.nanocomp.ctl.model.Variable;

public class Sum extends BasicFunction {

	public Sum(List<Variable> args) {
		super("+", args);
	}
	
	@Override
	public Object invoke() throws NoSuchMethodException {
		try {
			int result = 0;
			for(Variable v : getArgs()) {
				result += v.getIntegerValue();
			}
			return result;
		} catch (ClassCastException e) {
			double result = 0;
			for(Variable v : getArgs()) {
				result += v.getDoubleValue();
			}
			return result;
		}
	}
}
