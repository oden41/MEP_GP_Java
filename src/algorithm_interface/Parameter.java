package algorithm_interface;

public class Parameter {
	public static final int GP_INIT_TREE = 3;
	public static final int GP_MAX_TREE = 15;
	public static final boolean IS_FULL = true;

	public static int getNoOfChildren(String symbol) {
		if ("Sin".equals(symbol) || "Cos".equals(symbol) || "Log".equals(symbol) || "Not".equals(symbol))
			return 1;
		else if ("+".equals(symbol) || "*".equals(symbol) || "-".equals(symbol) || "And".equals(symbol)
				|| "Or".equals(symbol))
			return 2;
		else
			return 0;
	}
}
