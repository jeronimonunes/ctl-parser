package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class Variable implements Definable {
	
	public static final Variable ZERO = new Variable(null,0);
	
	public static final Variable NO_SIZE = new Variable(null,Double.NaN);
	
	public static final Variable INFINITY = new Variable(null,Double.POSITIVE_INFINITY);
	
	private String name;
	
	private Object value;
	
	public Variable() {
		super();
	}

	public Variable(String name, Object value) {
		this();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public double getDoubleValue() throws NoSuchMethodException {
		if(this.value instanceof Number) {
			return ((Number) this.value).doubleValue();
		} else if(this.value instanceof Variable) {
			return ((Variable) this.value).getDoubleValue();
		} else if(this.value instanceof Function) {
			return ((Number)((Function) this.value).invoke()).doubleValue();
		} else if(this.value instanceof String) {
			return Double.parseDouble((String) this.value);
		} else {
			throw new NumberFormatException("Not a number variable");
		}
	}
	
	public int getIntegerValue() throws NoSuchMethodException {
		if(this.value instanceof Number) {
			return (Integer)this.value;
		} else if(this.value instanceof Variable) {
			return ((Variable) this.value).getIntegerValue();
		} else if(this.value instanceof Function) {
			return (Integer) ((Function) this.value).invoke();
		} else if(this.value instanceof String) {
			return Integer.parseInt((String) this.value);
		} else {
			throw new NumberFormatException("Not a number variable");
		}
	}
	
	public Vector3 getVector3Value() throws NoSuchMethodException {
		if(this.value instanceof Vector3){
			return (Vector3) this.value;
		} else if(this.value instanceof Variable) {
			return ((Variable) this.value).getVector3Value();
		} else if(this.value instanceof Function) {
			return (Vector3) ((Function) this.value).invoke();
		} else {
			throw new NumberFormatException("Not a vector3 variable");
		}
	}
	
	@Override
	public void writeDefinition(Appendable appendable) throws IOException {
		if(this.name!=null && !this.name.isEmpty()) {
			appendable.append(name);
		} else {
			writeValueDefinition(appendable);
		}
	}
	
	private void writeValueDefinition(Appendable appendable) throws IOException {
		if(value==null){
			appendable.append(name);
		} else if(value instanceof Definable) {
			((Definable) value).writeDefinition(appendable);
		} else if(value instanceof CtlObject) {
			((CtlObject) value).write(appendable);
		} else if(value instanceof Number) {
			double d = ((Number) value).doubleValue();
			if(Double.isNaN(d)){
				appendable.append("no-size");
			} else if(d==Double.NEGATIVE_INFINITY || d==Double.POSITIVE_INFINITY) {
				appendable.append("infinity");
			} else {
				appendable.append(value.toString());
			}
		} else {
			appendable.append(value.toString());
		}
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		if(this.name!=null && !this.name.isEmpty()) {
			appendable.append(name);
		} else {
			writeValue(appendable);
		}
	}
	
	public void writeValue(Appendable appendable) throws IOException {
		if(value==null){
			appendable.append(name);
		} else if(value instanceof CtlObject) {
			((CtlObject) value).write(appendable);
		} else if(value instanceof Number) {
			double d = ((Number) value).doubleValue();
			if(Double.isNaN(d)){
				appendable.append("no-size");
			} else if(d==Double.NEGATIVE_INFINITY || d==Double.POSITIVE_INFINITY) {
				appendable.append("infinity");
			} else {
				appendable.append(value.toString());
			}
		} else {
			appendable.append(value.toString());
		}
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
