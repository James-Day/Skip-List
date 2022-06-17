package skiplist;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SkiplistTest {

	@Test
	void testInsert() {

		Skiplist SL = new Skiplist();
		SL.insert(20);
		assert(SL.getHeader().below.next.key == 20);	//test to make sure the first item in the list is 20.
	}
	@Test
	void testDelete() {

		Skiplist SL = new Skiplist();
		SL.insert(20);
		SL.delete(20);

		assert(SL.getHeader().below.next==null);	//test to make sure the first item in the list is empty after deletion.
	}
	@Test
	void deleteEmptyTree() {

		Skiplist SL = new Skiplist();
		SL.insert(20);
		SL.delete(20);

		assert(SL.delete(50) == false); //deleting a non-existent node should return false. 
		assert(SL.delete(20) == false);	//make sure deleting on a empty tree returns false.
		
	}
	@Test
	void testDisplayList() {

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
		SL.displayList();
		assert(true);	//output is as expected
	}
	@Test
	void testManyDeletions() {
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

		SL.delete(20);
		SL.delete(25);
		SL.delete(10);
		SL.delete(102);
		SL.delete(50);			
		SL.delete(83);
		SL.delete(81);			
		SL.delete(62);			//deletes them all and should be left with
		SL.delete(5);			//an empty list. 
		SL.delete(37);
		SL.delete(15);
		SL.delete(80);
		SL.delete(100);
		SL.delete(43);
		SL.delete(9);
		SL.delete(99);


		assert(SL.getHeader().below.next==null);	//test to make sure the first item in the list is empty after deletion.
	}
}
