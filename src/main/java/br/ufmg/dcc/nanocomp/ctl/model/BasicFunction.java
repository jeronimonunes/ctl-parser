package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class BasicFunction extends Function {
	
	private String name;

	private DefineFunction definition;

	public BasicFunction(String name, List<Variable> args) {
		super(args);
		this.name = name;
	}

	public BasicFunction(String name, List<Variable> args, DefineFunction definition) {
		this(name, args);
		this.definition = definition;
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(").append(getName());
		for(Variable arg : this.getArgs()) {
			appendable.append(" ");
			arg.write(appendable);
		}
		appendable.append(")");
	}
	
	@Override
	public void writeDefinition(Appendable appendable) throws IOException {
		appendable.append("(").append(getName());
		for(Variable arg : this.getArgs()) {
			appendable.append(" ");
			arg.writeDefinition(appendable);
		}
		appendable.append(")");
	}

	@Override
	public Object invoke() throws NoSuchMethodException {
		if(this.getDefinition() != null) {
			return getDefinition().getBody().invoke(this.getArgs().stream());
		} else {
			StringBuilder builder = new StringBuilder();
			try {
				this.write(builder);
			} catch (IOException e) {
				// ignore
			}
			throw new NoSuchMethodException(builder.toString());
		}
	}
	
	@Override
	public void fillVariables(Iterator<Variable> args) {
		this.getArgs().parallelStream().forEach(arg->{
			arg.setValue(args.next().getValue());
		});
	}

	@Override
	protected Object invoke(Stream<Variable> args) throws NoSuchMethodException {
		args = Stream.concat(args, this.getArgs().stream());
		if(getDefinition()!=null) {
			getDefinition().getName().fillVariables(args.iterator());
			return getDefinition().getBody().invoke();
		} else {
			throw new NoSuchMethodException(this.toString());
		}
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public DefineFunction getDefinition() {
		return definition;
	}

	public void setDefinition(DefineFunction definition) {
		this.definition = definition;
	}
}
