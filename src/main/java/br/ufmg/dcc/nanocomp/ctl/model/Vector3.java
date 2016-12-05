package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public class Vector3 implements CtlObject {

	private Variable x;

	private Variable y;

	private Variable z;

	public Vector3() {
		this.x = Variable.ZERO;
		this.y = Variable.ZERO;
		this.z = Variable.ZERO;
	}

	public Vector3(Variable x) {
		this();
		this.x = x;
	}

	public Vector3(Variable x, Variable y) {
		this(x);
		this.y = y;
	}

	public Vector3(Variable x, Variable y, Variable z) {
		this(x,y);
		this.z = z;
	}

	public Variable getX() {
		return x;
	}

	public void setX(Variable x) {
		this.x = x;
	}

	public Variable getY() {
		return y;
	}

	public void setY(Variable y) {
		this.y = y;
	}

	public Variable getZ() {
		return z;
	}

	public void setZ(Variable z) {
		this.z = z;
	}

	@Override
	public void write(Appendable appendable) throws IOException {
		appendable.append("(vector3 ");
		x.write(appendable);
		appendable.append(" ");
		y.write(appendable);
		appendable.append(" ");
		z.write(appendable);
		appendable.append(")");
	}

	@Override
	public String toString() {
		try {
			StringBuilder appendable = new StringBuilder();
			this.write(appendable);
			return appendable.toString();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
