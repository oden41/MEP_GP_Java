package mep;

import algorithm_interface.Element;

public class MEPElement extends Element {

	public MEPElement(String elem) {
		symbol = elem;
		index1 = -1;
		index2 = -1;
	}

	public MEPElement(String elem, int addr1, int addr2) {
		symbol = elem;
		index1 = addr1;
		index2 = addr2;
	}

	public int getIndex1() {
		return index1;
	}

	public int getIndex2() {
		return index2;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return " [ " + symbol + " ," + index1 + " ," + index2 + " ] ";
	}

}
