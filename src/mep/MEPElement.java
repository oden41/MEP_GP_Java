package mep;

import algorithm_interface.Element;

public class MEPElement extends Element implements Cloneable {

	public MEPElement(String elem) {
		super(elem);
	}

	public MEPElement(String elem, int addr1, int addr2) {
		super(elem, addr1, addr2);
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

	@Override
	public String toString() {
		return " [ " + symbol + " ," + index1 + " ," + index2 + " ] ";
	}

}
