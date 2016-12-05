package br.ufmg.dcc.nanocomp.ctl.model.functions;

import java.util.Iterator;
import java.util.List;

import br.ufmg.dcc.nanocomp.ctl.model.BasicFunction;
import br.ufmg.dcc.nanocomp.ctl.model.Variable;

public class Mul extends BasicFunction {

	public Mul(List<Variable> args) {
		super("*", args);
	}
	
	@Override
	public Object invoke() throws NoSuchMethodException {
		try {
			Iterator<Variable> values = getArgs().iterator();
			int result = 1;
			while(values.hasNext()) {
				result *= values.next().getIntegerValue();
			}
			return result;
		} catch (ClassCastException e) {
			Iterator<Variable> values = getArgs().iterator();
			double result = 1;
			while(values.hasNext()) {
				result *= values.next().getDoubleValue();
			}
			return result;
		}
	}
}
