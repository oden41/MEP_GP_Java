package gp;

import java.util.ArrayDeque;
import java.util.Deque;

import algorithm_interface.Element;
import algorithm_interface.Parameter;
import experiment.Experiment;

public class GPElement extends Element implements Cloneable {
	int id;
	GPElement[] children;
	int tempDepth;

	public GPElement(String elem) {
		super(elem);
		children = new GPElement[0];
	}

	/* (非 Javadoc)
	 * 基本的に木構造はノード先が分かるため，indexを使用せず
	 * @see java.lang.Object#clone()
	 */
	@Override
	public GPElement clone() {
		GPElement elem = null;
		try {
			elem = (GPElement) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return elem;
	}

	GPElement initialize(int gpInitTree, boolean isFull, String[] nonTerminals, String[] terminals) {
		if (gpInitTree <= 0) {
			//終端記号を設定
			String terminal = terminals[Experiment.random.nextInt(terminals.length)];
			return new GPElement(terminal);
		}

		String symbol = "";
		if (isFull || Experiment.random.nextInt(2) == 0) {
			//非終端記号
			symbol = nonTerminals[Experiment.random.nextInt(nonTerminals.length)];
		} else {
			//終端記号
			symbol = terminals[Experiment.random.nextInt(terminals.length)];
		}
		GPElement node = new GPElement(symbol);
		node.children = new GPElement[Parameter.getNoOfChildren(symbol)];
		for (int i = 0; i < node.children.length; i++) {
			node.children[i] = new GPElement("");
			node.children[i] = node.children[i].initialize(gpInitTree - 1, isFull, nonTerminals, terminals);
		}
		return node;
	}

	/**
	 * デバッグ用のメソッド<br>
	 * 木構造をタブにより表現する
	 *
	 * @see java.lang.Object#toString()
	 */
	public String getTreeString() {
		String str = "";
		Deque<GPElement> stack = new ArrayDeque<>();
		tempDepth = 0;
		stack.push(this);
		while (!stack.isEmpty()) {
			GPElement node = stack.poll();
			int height = node.tempDepth;
			str += repeat("||----- ", height) + node.getSymbol() + "\n";

			for (int i = 0; i < node.children.length; i++) {
				node.children[i].tempDepth = height + 1;
				stack.push(node.children[i]);
			}
		}
		return str;
	}

	private static String repeat(String str, int count) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < count; i++) {
			if (i != count - 1)
				buf.append("        ");
			else
				buf.append(str);
		}
		return buf.toString();
	}
}
