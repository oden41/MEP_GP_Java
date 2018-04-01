package algorithm_interface;

public class Element {
	protected String symbol;
	protected int index1;
	protected int index2;

	public Element(String elem) {
		symbol = elem;
		index1 = -1;
		index2 = -1;
	}

	public Element(String elem, int addr1, int addr2) {
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

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
