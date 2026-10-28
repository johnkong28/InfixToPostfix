import java.util.Stack;
public class ArrayStack<T> extends Stack<T> {
    private T[] items;
    private int n;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        items = (T[]) new Object[1000];
        n = 0;
    }

    @Override
    public T push(T item) {
        // TODO: If the array is full, double its capacity; then store item at index n and increment n.
        items[n] = item;
        n++;
        return item;
    }

    @Override
    public T pop() {
        // TODO: Decrement n, save and return that item, and set the popped slot to null to avoid loitering.

        T thig = items[n-1];
        items[n-1]= null;
        n--;
        return thig;
    }

    @Override
    public boolean isEmpty() {
        // TODO: Return true when n is zero.

        return n==0;
    }

    @Override
    public int size() {
        // TODO: Return the number of items currently in the stack.
        return n;
    }

    /**
     * Challenge: Given a string containing '(', ')', '{', '}', '[', and ']'
     * return true if every opening bracket is closed by the same type in the correct order.
     * The empty string is valid.
     */
    public static boolean isValid(String s) {
        // TODO: Use an ArrayStack<Character> to check matching brackets. Push opening brackets;
        // for each closer, check that the most recent opener has the matching type.
        ArrayStack<Character> Thtack = new ArrayStack();
        char Thog;
        for (int i = 0; i < s.length();i++){
            Thog = s.charAt(i);
            if (Thog == '{' || Thog == '[' || Thog == '(' ){
                Thtack.push(Thog);
            } else {

                char Thig = Thtack.pop();
                if (Thig == '{' && Thog != '}'){
                    return false;
                }
                if (Thig == '(' && Thog != ')'){
                    return false;
                }
                if (Thig == '[' && Thog != ']'){
                    return false;
                }
            }
        }
        return Thtack.isEmpty();
    }

    static void check(String label, Object actual, Object expected) {
        if (actual.equals(expected))
            System.out.println("PASS | " + label);
        else
            System.out.println("FAIL | " + label + " | expected: " + expected + " | got: " + actual);
    }

    public static void main(String[] args) {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("first");
        stack.push("second");
        stack.push("third");

        check("size after 3 pushes", stack.size(), 3);
        check("isEmpty after pushes", stack.isEmpty(), false);
        check("pop 1 (LIFO)", stack.pop(), "third");
        check("pop 2 (LIFO)", stack.pop(), "second");
        check("pop 3 (LIFO)", stack.pop(), "first");
        check("isEmpty after all pops", stack.isEmpty(), true);

        check("isValid: \"[]\"", isValid("[]"), true);
        check("isValid: \"([{}])\"", isValid("([{}])"), true);
        check("isValid: \"[(])\"", isValid("[(])"), false);
        check("isValid: empty string", isValid(""), true);
        check("isValid: \"({[]})\"", isValid("({[]})"), true);
        check("isValid: \"(((\"", isValid("((("), false);
    }
}
