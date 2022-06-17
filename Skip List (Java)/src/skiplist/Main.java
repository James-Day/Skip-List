package skiplist;

public class Main {

	public static void main(String[] args) {

		Skiplist SL = new Skiplist();
		SL.insert(20);
		SL.insert(25);
		SL.insert(10);
		SL.insert(80);
		SL.insert(50);
		SL.insert(83);
		SL.insert(81);
		SL.insert(62);
		SL.insert(5);
		SL.insert(9);
		SL.insert(15);
		SL.insert(99);
		SL.insert(100);
		SL.insert(43);
		SL.insert(37);
		SL.insert(102);
		SL.delete(9);
		SL.delete(102);
		SL.delete(5);
		SL.delete(50);
		SL.displayList();

		
	}

}
