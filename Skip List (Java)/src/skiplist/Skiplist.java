package skiplist;

public class Skiplist {

	private SLNode header = null;
	int sentinelHeight = 0;

	Skiplist() {

	}

	public boolean insert(int key) {
		boolean heads = true;
		int height = 0;
		int curHeight = 0;
		SLNode cur = getHeader();
		SLNode above = null;

		if (getHeader() == null) {
			setHeader(new SLNode(0));
		}

		while (heads == true) {
			if (Math.random() > 0.5) {
				heads = true;
			} else {
				heads = false;
			}
			height++;
		}
		height--; // this makes it so the height starts at zero i.e height of 2 is 3 nodes tall
		while (height >= sentinelHeight) { // increases sentinel height if needed.
			cur = new SLNode(0);
			cur.below = getHeader();
			setHeader(cur);
			sentinelHeight++;
		}
		cur = getHeader().below;
		curHeight = sentinelHeight - 1;
		while (curHeight != -1) { // makes sure we don't go below the bottom;
			while (cur.next != null && cur.next.key < key) {
				cur = cur.next;
			}
			if (height >= curHeight) {
				SLNode ins = new SLNode(key);
				ins.next = cur.next;
				cur.next = ins;
				if (above != null) {
					above.below = ins;
				}
				above = ins;
			}
			cur = cur.below;
			curHeight--;
		}
		return true;
	}

	public boolean delete(int key) {
		SLNode cur = getHeader();
		SLNode previous = cur;
		int curheight = sentinelHeight;
		cur = cur.below;
		curheight--;
		while (curheight > -1 && cur.key != key) {
			while (cur.next != null && cur.next.key <= key) {
				previous = cur;
				cur = cur.next;
			}
			if (cur.key != key) {// in sentinel or as far right as you can go, but not at the key
				cur = cur.below;
				curheight--;
			}
		}
		if (curheight == -1) {
			return false; // if the key wasn't found then return false
		}
		while (curheight != 0) {
			previous.next = cur.next;
			previous = previous.below;
			while (previous.next != null && previous.next.key < key) {
				previous = previous.next;
			}
			cur = cur.below;
			curheight--;
		}
		previous.next = cur.next;
		cur = null;
		return true;
	}

	public void displayList() {
		int height = sentinelHeight + 1;
		SLNode cur = getHeader();
		SLNode head = getHeader();
		while (height > 1) {
			head = head.below;
			cur = head;
			height--;
			if (cur.next != null) {
				cur = cur.next;
			}
			if (cur.next != null || cur.key != 0) { // make sure that the sentinel isn't being printed
				System.out.print("Level " + height + ": ");
				while (cur != null) {
					System.out.print(cur.key + " ");
					cur = cur.next;
				}

				System.out.println();
			}
		}
	}

	public SLNode getHeader() {
		return header;
	}

	public void setHeader(SLNode header) {
		this.header = header;
	}

}
