package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import br.ufmg.dcc.nanocomp.ctl.parser.ParserFactory;
import br.ufmg.dcc.nanocomp.peg.Parser;

public class FunctionWrapper extends Function {

	private Function function;

	public FunctionWrapper() {
		super();
	}

	public FunctionWrapper(Function function, List<Variable> args) {
		super(args);
		this.function = function;
	}

	public static void main(String[] args) throws IOException, NoSuchMethodException {
		try(Writer out = new OutputStreamWriter(System.out)){
			Parser<File> parser = ParserFactory.getInstance().getParser();
			File file = parser.parse("(define (((fac a b) c) d) (* a (- b c (+ 7.2 d)))) (((fac 2 3) 10) 20)");
			@SuppressWarnings("unused")
			DefineFunction def = (DefineFunction) file.getObjects().get(0);
			FunctionWrapper f = (FunctionWrapper) file.getObjects().get(1);
			System.out.println(f.invoke());
			file.write(System.out);
		}
	}
	
	@Override
	public void fillVariables(Iterator<Variable> args) {
		this.getArgs().parallelStream().forEach(arg->{
			arg.setValue(args.next().getValue());
		});
		getFunction().fillVariables(args);
	}

	public Object invoke() throws NoSuchMethodException {
		return this.function.invoke(this.getArgs().stream());
	}

	@Override
	protected Object invoke(Stream<Variable> args) throws NoSuchMethodException {
		return this.function.invoke(Stream.concat(args, getArgs().stream()));
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(");
		this.function.write(appendable);
		for(Variable arg : this.getArgs()) {
			appendable.append(" ");
			arg.writeValue(appendable);
		}
		appendable.append(")");
	}
	
	public void writeDefinition(Appendable appendable) throws IOException {
		appendable.append("(");
		this.function.writeDefinition(appendable);
		for(Variable arg : this.getArgs()) {
			appendable.append(" ");
			arg.write(appendable);
		}
		appendable.append(")");
	}
	
	public Function getFunction() {
		return function;
	}
	
	public void setFunction(Function function) {
		this.function = function;
	}

	@Override
	public String toString() {
		try {
			StringBuilder appendable = new StringBuilder();
			write(appendable);
			return appendable.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
