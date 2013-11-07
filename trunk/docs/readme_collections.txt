/****************************************************************************************************/
/*								Basics of Generics
/****************************************************************************************************/
There's a very simple rule type of the variable declaration must match the type you pass to the 
actual object type. If you declare List<Foo> foo then whatever you assign to the foo reference MUST
 be of the generic type <Foo>. Not a subtype of <Foo>. Not a supertype of <Foo>. Just <Foo>.
 
These are wrong:
List<Object> myList = new ArrayList<JButton>(); // NO!
List<Number> numbers = new ArrayList<Integer>(); // NO!
// remember that Integer is a subtype of Number

But these are fine:
List<JButton> myList = new ArrayList<JButton>(); // yes
List<Object> myList = new ArrayList<Object>(); // yes
List<Integer> myList = new ArrayList<Integer>(); // yes





/****************************************************************************************************/
/*								Basics of Collections
/****************************************************************************************************/
Collection
Collections

List
	ArrayList
	LinkedList
	Vector
	Stack
	
Queue
	LinkedList
	PriorityQueue
	
BlockingQueue	(Producer/Consumer)
	ArrayBlockingQueue
	LinkedBlockingQueue
	PriorityBlockingQueue
	
			Throws exception		Special value			Blocks			Times out
Insert		add(e)					offer(e)				put(e)			offer(e, time, unit)
Remove		remove()				poll()					take()			poll(time, unit)
Examine		element()				peek()					N/A				N/A	
	

Set
	HashSet
	TreeSet
	LinkedHashSet
	
Map
	HashMap
	HashTable
	TreeHashMap
	LinkedHashMap