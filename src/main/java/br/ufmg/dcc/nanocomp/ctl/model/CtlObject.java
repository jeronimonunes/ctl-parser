package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public interface CtlObject {

	public void write(Appendable appendable) throws IOException;
}
