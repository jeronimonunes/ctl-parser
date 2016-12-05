package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public abstract class Function implements Definable {

	private List<Variable> args;
	
	public Function() {
		super();
	}

	public Function(List<Variable> args) {
		this();
		this.args = args;
	}

	public List<Variable> getArgs() {
		return args;
	}
	
	public void setArgs(List<Variable> args) {
		this.args = args;
	}

	public abstract Object invoke() throws NoSuchMethodException;
	
	public abstract void fillVariables(Iterator<Variable> args2);

	protected abstract Object invoke(Stream<Variable> stream) throws NoSuchMethodException;

	public abstract void writeDefinition(Appendable appendable) throws IOException;

}
