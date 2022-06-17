#pragma once
#include <string>

using namespace std;

template <typename KeyComparable, typename Value >
class skipList {			//Use interface later
private:
	class BinaryNode {
	public:
		KeyComparable key;
		Value value;

		BinaryNode* below;
		BinaryNode* next;
		//Initialize class members from constructor arguments 
		//by using a member initializer list.
		//This method uses direct initialization, which is more
		//efficient than using assignment operators inside the constructor body.
		BinaryNode(KeyComparable& key, Value& value, BinaryNode* below = nullptr, BinaryNode* next = nullptr)
			: value{ value }, below{ below }, next{ next }, key{ key }
		{

		}
		BinaryNode()
			: value{0}, below{ nullptr }, next{ nullptr }, key{0}{}
	};
public:
	// the head node of the skiplist
	BinaryNode* head = nullptr;
	int sentinalHeight = 0; //amount of levels in skipList

	bool insert(Value value, KeyComparable key) {
		return(this->insert(value,key, new BinaryNode(key, value)));
	}
	bool insert(Value item, KeyComparable key, BinaryNode* node)
	{
		bool heads = true;
		int height = 0;
		int currHeight = 0;
		BinaryNode* currPointer = head;
		BinaryNode* previous = head;
		BinaryNode* temp = nullptr;
		BinaryNode* above = nullptr;


		if (head == nullptr) {
			head = new BinaryNode();
			sentinalHeight++;
		}
		
		while (heads == true) {
			heads = rand() % 2;
			height++;
		} 
		height--;					// a  height of 2 means that there are 3 levels

		while (height +2> sentinalHeight) {
			currPointer = new BinaryNode();
			currPointer->below = head;
			head = currPointer;
			sentinalHeight++;
		}		
		currPointer = head->below; // currpointer should point 1 below the head
			currHeight = sentinalHeight - 2;
		while (currHeight !=-1) {	
			
			previous = currPointer;

				while (currPointer->next !=nullptr && currPointer->next->key < key) {
				previous = currPointer;																//Go left
				currPointer = currPointer->next;
				}
				if (height >= currHeight) {
					
					temp = new BinaryNode(key, item, nullptr, nullptr);
					temp->next = currPointer->next;												//insert if needed
					currPointer->next = temp;
					if (above != nullptr) {
						above->below = temp;
					}
					above = temp;
				}
				currPointer = previous->below;
				currHeight--;
		}
		return true;
	}
	bool remove(const KeyComparable& key) {
		BinaryNode* currpointer = head;
		BinaryNode* previous = currpointer;
		BinaryNode* deletePointer = nullptr;

		currpointer = currpointer->below;
		int currheight = sentinalHeight - 2;
		while (currheight!=-1 && currpointer->key != key ) {
			while (currpointer->next != nullptr && currpointer->next->key <= key) {
				previous = currpointer;
				currpointer = currpointer->next;
			}
			if (currpointer->key != key ) {	//if you are in the sentinal or as far right as you can go.
				currpointer = currpointer->below;
				currheight--;
			}
		}
		if (currheight == -1) {
			return false;					//if you didn't find the key return false
		}
		else {
			while (currheight != 0) {
				previous->next = currpointer->next;   //found the key delete the tower
				previous = previous->below;
				while (previous->next !=nullptr && previous->next->key < key) {
					previous = previous->next;
				}
				deletePointer = currpointer;
				currpointer = currpointer->below;
				delete deletePointer;
				currheight--;
			}
			previous->next = currpointer->next;
			delete currpointer;
		}
	}
	void displayList(ostream& out) {
		int height = sentinalHeight;
		BinaryNode* currpointer = head;
		BinaryNode* headPointer = head;
		while (height > 1) {

			headPointer = headPointer->below;
			currpointer = headPointer;
			height--;
			if (currpointer->next != nullptr) {
				currpointer = currpointer->next;
			}
			cout << "Level " << height <<": ";
			while (currpointer != nullptr) {
				cout << currpointer->key <<" ";
				currpointer = currpointer->next;
			}
			cout << endl << endl;
		}
	}
};