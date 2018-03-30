package mep;

import algorithm_interface.Element;

public class MEPElement extends Element implements Cloneable {

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

	@Override
	public MEPElement clone() {
		MEPElement elem = null;
		try {
			elem = (MEPElement) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return elem;
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
