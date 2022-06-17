package skiplist;

public class SLNode {
	int key;
	SLNode below;
	SLNode next;

	SLNode(int num) {
		key = num;
		below = null;
		next = null;
	}

	SLNode(int num, SLNode bottom, SLNode ahead) {
		key = num;
		below = bottom;
		next = ahead;
	}
}
