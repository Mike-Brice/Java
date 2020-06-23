import java.util.Stack;

public class UndoRedo<T> {

	private Stack<T> undo;
	private Stack<T> redo;
	
	public UndoRedo() {
		undo = new Stack<T>();
		redo = new Stack<T>();
	}
	
	// Undo
	public void addAction(T item) {
		undo.push(item);
	}
	
	public T getAction() {
		T redo = undo.pop();
		addUndo(redo);
		return redo;
	}
	
	// Redo 
	public void addUndo(T item) {
		redo.push(item);
	}
	
	public T getUndo() {
		return redo.pop();
	}
}

// stack[0] = {object reference, object state}

class Element<E> {
	
	public Element(E reference, E state) {
		
	}
}