
import java.util.Scanner;
import java.util.Stack;
/**
 * InfixCalculator.java
 * DATA STRUCTURES 5(A) — Jesuit High School
 *
 * Full pipeline: Infix expression → Postfix → Evaluated integer result.
 *
 * HOW TO USE THIS FILE:
 * 1. Your convert() method below is identical to InfixToPostfix.java.
 * Complete it the same way (or copy your finished version over).
 * 2. Find the evaluatePostfix() method stub below.
 * Copy your completed evaluatePostfix() method from last week's
 * assignment into that stub — replace the TODO body only,
 * keeping the method signature exactly as written.
 * 3. The evaluate() method chains them together automatically.
 * 4. Run main() to check all test cases.
 *
 * Assumptions:
 * - Tokens are separated by single spaces.
 * - Operands are non-negative integers.
 * - Operators: + - * / (integer division, truncates toward zero)
 * - Parentheses: ( )
 */
public class InfixCalculator {
// ================================================================
// PART 1 — Infix to Postfix Converter
// (Same algorithm as InfixToPostfix.java — complete or copy over)
// ================================================================
    /** Returns precedence: 2 for * /, 1 for + -, 0 otherwise. */
    private static int precedence(String op) {
// TODO: return 2 for "*" and "/", 1 for "+" and "-", 0 otherwise
        if (op.equals("*") || op.equals("/")){
            return 2;
        }
        if (op.equals("+") || op.equals("-")){
            return 1;
        }
        return 0;
    }
// ---------------------------------------------------------------
// Helper: Is Operator?
// ---------------------------------------------------------------
    /**
     * Returns true if the token is one of the four operators: + - * /
     *
     * @param token the token to check
     * @return true if token is an operator
     */
    private static boolean isOperator(String token) {
// TODO: return true if token equals "+", "-", "*", or "/"
        if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
            return true;
        }
        return false;
    }
    private static boolean isParen(String token) {
        return (token.equals("(") || token.equals(")"));
    }
// ---------------------------------------------------------------
// Core Algorithm: Infix → Postfix
// ---------------------------------------------------------------
    /**
     * Converts a space-separated infix expression to a space-separated
     * postfix expression using the Shunting Yard algorithm.
     *
     * Algorithm steps for each token:
     * 1. Operand (number) → append to output
     * 2. "(" → push onto stack
     * 3. ")" → pop to output until "(" is found; discard
     "("
     * 4. Operator → while stack top is not "(" and has >=
     precedence,
     * pop to output; then push current operator
     * 5. End of input → pop all remaining operators to output
     *
     * @param infix a space-separated infix expression, e.g. "( 3 + 4 )"
     * @return the equivalent postfix expression, e.g. "3 4 +"
     */
    public static String convert(String infix) {
        Stack<String> stack = new Stack<>();
        StringBuilder output = new StringBuilder();

        Scanner thcan = new Scanner(infix);
        while (thcan.hasNext()) {
            String necktht = thcan.next();

            if (!isParen(necktht) && !isOperator(necktht)) {
                output.append(necktht).append(" ");
            } else if (necktht.equals("(")) {
                stack.push(necktht);
            } else if (necktht.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.append(stack.pop()).append(" ");
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && !stack.peek().equals("(") && precedence(stack.peek()) >= precedence(necktht)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(necktht);
                }
            }
        while (!stack.isEmpty()) {
            String thith= stack.pop();
            output.append(thith).append(" ");
        }
        return output.toString().trim();
    }
// ================================================================
// PART 2 — Postfix Evaluator
// Copy your completed method from last week's assignment here.
// Keep the method signature exactly as written below.
// ================================================================
    /**
     * Evaluates a space-separated postfix expression and returns the
     * integer result.
     *
     * Algorithm reminder:
     * - Scan tokens left to right.
     * - Operand → push onto stack.
     * - Operator → pop two operands (b then a), apply operator,
     * push result back onto stack.
     * - End of input → the single value left on the stack is the answer.
     *
     * @param postfix e.g. "3 4 +"
     * @return integer result, e.g. 7
     */
    public static int evaluatePostfix(String postfix) {
        Stack<Integer> Thtack = new Stack<>();
        Scanner scannedTuna = new Scanner(postfix);
        while (scannedTuna.hasNext()) {
            String Necktht = scannedTuna.next();

            if (!isOperator(Necktht)) {
                Thtack.push(Integer.parseInt(Necktht));
            } else {
                int b = Thtack.pop();
                int a = Thtack.pop();
                switch (Necktht) {
                    case "+":
                        Thtack.push(a + b);
                        break;
                    case "-":
                        Thtack.push(a - b);
                        break;
                    case "/":
                        Thtack.push(a / b);
                        break;
                    case "*":
                        Thtack.push(a * b);
                        break;

                    }
                }
            }
        return Thtack.pop();
    }
// ================================================================
// PART 3 — Full Pipeline
// No changes needed here — this wires Parts 1 and 2 together.
// ================================================================
    /**
     * Converts an infix expression to postfix, then evaluates it.
     *
     * @param infix a space-separated infix expression
     * @return the integer result of the expression
     */
    public static int evaluate(String infix) {
        String postfix = convert(infix);
        System.out.println(" Postfix : " + postfix);
        return evaluatePostfix(postfix);
    }
// ================================================================
// Test Cases
// ================================================================
    /**
     * Runs a single end-to-end test.
     *
     * @param infix the infix expression to evaluate
     * @param expected the expected integer result
     */
    private static void runTest(String infix, int expected) {
        System.out.println("Infix : " + infix);
        int result = evaluate(infix);
        boolean passed = result == expected;
        System.out.println("Expected: " + expected);
        System.out.println("Got : " + result);
        System.out.println(passed ? "✓ PASS" : "✗ FAIL");
        System.out.println("---------------------------------------------- ----");
    }
    public static void main(String[] args) {
        System.out.println("============================================== ====");
                System.out.println(" InfixCalculator — End-to-End Test Suite");
        System.out.println("============================================== ====\n");
// Test 1: Simple addition
// ( 3 + 4 ) → postfix: 3 4 + → result: 7
                runTest("( 3 + 4 )", 7);
// Test 2: Division over parenthesized sum
// 10 / ( 2 + 3 ) → postfix: 10 2 3 + / → result: 2
        runTest("10 / ( 2 + 3 )", 2);
// Test 3: Nested parentheses
// 100 / ( ( 4 - 2 ) * 25 ) → postfix: 100 4 2 - 25 * / → result:2
        runTest("100 / ( ( 4 - 2 ) * 25 )", 2);
// Test 4: Deeply nested, mixed operators, negative intermediate
// ( ( ( 3 - 5 ) * ( 2 + 7 ) ) / ( 8 - 5 ) )
// → postfix: 3 5 - 2 7 + * 8 5 - /
// → (3-5)=-2, (2+7)=9, -2*9=-18, (8-5)=3, -18/3=-6
        runTest("( ( ( 3 - 5 ) * ( 2 + 7 ) ) / ( 8 - 5 ) )", -6);
        System.out.println("============================================== ====");
                System.out.println(" All tests complete.");
        System.out.println("============================================== ====");
    }
}
