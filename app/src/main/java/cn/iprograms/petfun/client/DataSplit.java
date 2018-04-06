package cn.iprograms.petfun.client;

class DataSplit {
	public DataSplit() {
	}
	public static String getHeadtOrder(String data) {
		String[] oder=data.split("<divide>", 2);
		return oder[0];
	}
	public static String[] getBodyOrder(String data) {
		String[] order=data.split("<divide>");
		return order;
	}
}
