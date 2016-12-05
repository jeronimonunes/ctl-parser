package br.ufmg.dcc.nanocomp.ctl.model;

import java.io.IOException;

public interface Definable extends CtlObject {

	void writeDefinition(Appendable appendable) throws IOException;

}
