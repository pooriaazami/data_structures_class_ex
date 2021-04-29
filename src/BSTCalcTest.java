import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

class Calculator {
    public static ArrayList<Token> translateToPostOrder(ArrayList<Token> inOrder) {
        ArrayList<Token> ans = new ArrayList<>();
        Stack<Token> stack = new Stack<>();

        int len = inOrder.size();
        for (int i = 0; i < len; i++) {
            Token current = inOrder.get(i);

            if (current.isNumber()) {
                ans.add(current);
            } else {
                if (current.isLeftParenthesis())
                    stack.add(current);
                else if (current.isRightParenthesis()) {
                    while (true) {
                        if (stack.peek().isLeftParenthesis())
                            break;

                        ans.add(stack.pop());
                    }
                    stack.pop();
                } else {
                    while (stack.size() > 0 && current.compareTo(stack.peek()) < 1) {
                        ans.add(stack.pop());
                    }

                    stack.push(current);
                }
            }
        }

        while (stack.size() > 0) {
            ans.add(stack.pop());
        }

        return ans;
    }

    private static void calculateTokens(Token operator, Stack<Double> numbers) {
        double operand1, operand2;
        switch (operator.getValue()) {
            case "+":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand1 + operand2);
                return;
            case "-":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand2 - operand1);
                return;
            case "*":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand1 * operand2);
                return;
            case "/":
            case "\\":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(operand2 / operand1);
                return;
            case "^":
            case "power":
                operand1 = numbers.pop();
                operand2 = numbers.pop();
                numbers.push(Math.pow(operand2, operand1));
                return;
            case "sqrt":
                operand1 = numbers.pop();
                numbers.push(Math.sqrt(operand1));
                return;
            case "sin":
                operand1 = numbers.pop();
                numbers.push(Math.sin(operand1));
                return;
            case "cos":
                operand1 = numbers.pop();
                numbers.push(Math.cos(operand1));
                return;
            case "tan":
            case "tg":
                operand1 = numbers.pop();
                numbers.push(Math.tan(operand1));
                return;
            case "cot":
                operand1 = numbers.pop();
                numbers.push(1 / Math.tan(operand1));
                return;
            case "sinh":
                operand1 = numbers.pop();
                numbers.push(Math.sinh(operand1));
                return;
            case "cosh":
                operand1 = numbers.pop();
                numbers.push(Math.cosh(operand1));
                return;
            case "tanh":
                operand1 = numbers.pop();
                numbers.push(Math.tanh(operand1));
                return;
            case "log":
                operand1 = numbers.pop();
                numbers.push(Math.log10(operand1));
                return;
            case "ln":
                operand1 = numbers.pop();
                numbers.push(Math.log(operand1));
                return;
            case "exp":
                operand1 = numbers.pop();
                numbers.push(Math.exp(operand1));
                return;
            case "abs":
                operand1 = numbers.pop();
                numbers.push(Math.abs(operand1));
        }
    }

    public static double calculatePostOrder(ArrayList<Token> postOrder) {
        Stack<Double> stack = new Stack<>();

        int len = postOrder.size();
        for (int i = 0; i < len; i++) {
            Token current = postOrder.get(i);

            if (current.isNumber()) {
                stack.push(current.parseToDouble());
            } else {
                calculateTokens(current, stack);
            }
        }

        if (stack.size() != 1)
            throw new RuntimeException("Invalid expression");

        return stack.peek();
    }

    public static double calculate(String expression) {
        ArrayList<Token> tokenizedExpression = Tokenizer.tokenize(expression);
        ArrayList<Token> postOrderExpression = Calculator.translateToPostOrder(tokenizedExpression);

        return calculatePostOrder(postOrderExpression);
    }
}

class Tokenizer {
    public static boolean isNumber(char c) {
        return (c >= '0' && c <= '9') || c == '.';
    }

    public static boolean isParenthesis(char c) {
        return c == '(' || c == ')';
    }

    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '\\' || c == '^' || c == '/';
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static void processBuffer(String buffer, boolean isBufferNumeric, ArrayList<Token> tokens) {
        if (buffer.length() > 0) {
            if (buffer.equals("pi")) {
                tokens.add(new Token("3.141592653589793", Token.Priorities.NUMBER));
                return;
            }
            if (buffer.equals("e")) {
                tokens.add(new Token("2.718281828459045", Token.Priorities.NUMBER));
                return;
            }

            if (isBufferNumeric) {
                tokens.add(new Token(buffer, Token.Priorities.NUMBER));
            } else {
                tokens.add(new Token(buffer, Token.Priorities.FUNCTION));
            }
        }
    }

    public static ArrayList<Token> tokenize(String expression) {
        ArrayList<Token> ans = new ArrayList<>();

        String buffer = "";
        boolean isBufferNumeric = true;
        int len = expression.length();
        for (int i = 0; i < len; i++) {

            if (expression.charAt(i) == ' ')
                continue;

            if (isParenthesis(expression.charAt(i))) {
                processBuffer(buffer, isBufferNumeric, ans);
                buffer = "";
                ans.add(new Token(String.valueOf(expression.charAt(i)), Token.Priorities.PARENTHESES));
            } else if (isOperator(expression.charAt(i))) {
                processBuffer(buffer, isBufferNumeric, ans);
                buffer = "";
                switch (expression.charAt(i)) {
                    case '+' -> ans.add(new Token("+", Token.Priorities.SUM));
                    case '-' -> {
                        if (ans.size() == 0 || !ans.get(ans.size() - 1).isNumber()) {
                            isBufferNumeric = true;
                            buffer += "-";
                        } else
                            ans.add(new Token("-", Token.Priorities.SUM));
                    }
                    case '*' -> ans.add(new Token("*", Token.Priorities.PRODUCT));
                    case '/', '\\' -> ans.add(new Token("\\", Token.Priorities.PRODUCT));
                    case '^' -> ans.add(new Token("^", Token.Priorities.FUNCTION));
                }
            } else if (isLetter(expression.charAt(i))) {
                if (buffer.length() > 0 && isBufferNumeric)
                    throw new IllegalArgumentException("Invalid expression");

                buffer += expression.charAt(i);
                isBufferNumeric = false;
            } else if (isNumber(expression.charAt(i))) {
                if (buffer.length() > 0 && !isBufferNumeric)
                    throw new IllegalArgumentException("Invalid expression");

                buffer += expression.charAt(i);
                isBufferNumeric = true;
            } else {
                throw new IllegalArgumentException("Invalid expression");
            }

        }

        processBuffer(buffer, isBufferNumeric, ans);

        return ans;
    }
}

class Token implements Comparable {
    private final String value;
    private final Priorities priorities;
    private final Type type;

    public Token(String value, Priorities priorities) {
        this.value = value;
        this.priorities = priorities;

        if (priorities == Priorities.NUMBER)
            this.type = Type.NUMBER;
        else
            this.type = Type.FUNCTION;
    }

    public String getValue() {
        return value;
    }

    public Priorities getPriorities() {
        return priorities;
    }

    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Token)) {
            throw new IllegalArgumentException("You can compare token with token");
        }

        Token token = (Token) o;
        if (token.type != this.type) {
            throw new IllegalArgumentException("You can only compare tokens with same type");
        }

        if (this.getPriorities().getValue() < token.getPriorities().getValue())
            return 1;
        else if (this.getPriorities().getValue() > token.getPriorities().getValue())
            return -1;

        return 0;

    }

    public boolean isNumber() {
        return this.type.value == Type.NUMBER.value;
    }

    public boolean isLeftParenthesis() {
        return this.value.equals("(");
    }

    public boolean isRightParenthesis() {
        return this.value.equals(")");
    }

    public boolean isOperator() {
        return !this.isNumber();
    }

    public enum Priorities {
        FUNCTION(1), PRODUCT(2), SUM(3), NUMBER(4), PARENTHESES(5);

        private int value;

        Priorities(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    private enum Type {
        NUMBER(1), FUNCTION(2);
        private int value;

        Type(int value) {
            this.value = value;
        }
    }

    public double parseToDouble() {
        if (!this.isNumber()) {
            throw new IllegalArgumentException("You can not parse this token to double");
        }

        return Double.parseDouble(this.value);
    }

    @Override
    public String toString() {
        return "Token{" +
                "value='" + value + '\'' +
                ", priority=" + priorities +
                ", type=" + type +
                '}';
    }
}

class BSTCalc {

    private Node root;

    public BSTCalc() {
        this.root = null;
    }

    public BSTCalc(String expression) {
        buildFromExpression(expression);
    }

    public void buildFromExpression(String expression) {
        ArrayList<Token> nodes = Tokenizer.tokenize(expression);
        nodes = Calculator.translateToPostOrder(nodes);
        Stack<Node> stack = new Stack<>();

        for (Token token : nodes) {
            if (token.isNumber()) {
                Node node = new Node(token);
                stack.push(node);
            } else {
                Node node1 = stack.pop();
                Node node2 = stack.pop();

                Node newNode = new Node(token, node1, node2);
                stack.push(newNode);
            }
        }

        this.root = stack.pop();
    }

    private int getHeight(Node node) {
        if (node == null)
            return 0;

        if (node.isLeaf())
            return 1;

        return 1 + Integer.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    public int getHeight() {
        return getHeight(this.root);
    }

    private void printTree(int tab, Node node) {
        if (node == null)
            return;

        for (int i = 0; i < tab; i++)
            System.out.print("\t");

        System.out.println(node.getValue());

        printTree(tab + 1, node.getLeft());

        printTree(tab + 1, node.getRight());

    }

    public void printTree() {
        printTree(0, root);
    }


    private static class Node {
        private Token _value;

        private Node _left;
        private Node _right;

        public Node(Token value) {
            this._value = value;
        }

        public Node(Token value, Node left, Node right) {
            this._value = value;
            this._left = left;
            this._right = right;
        }

        public Node getLeft() {
            return this._left;
        }

        public Node getRight() {
            return this._right;
        }

        public void setLeft(Node node) {
            this._left = node;
        }

        public void setRight(Node node) {
            this._left = node;
        }

        public boolean isLeaf() {
            return this._left == null && this._right == null;
        }

        public Token getValue() {
            return this._value;
        }

        public enum TYPE {
            NUMBER(0), OPERATOR(1);

            private int value;

            TYPE(int value) {
                this.value = value;
            }

            public int getValue() {
                return this.value;
            }
        }
    }

}


public class BSTCalcTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String exp = input.nextLine();

        String str = "";
        for (char c : exp.toCharArray()) {
            if (c != ' ')
                str += c;
        }

        BSTCalc bstCalc = new BSTCalc();
        bstCalc.buildFromExpression(str);

        System.out.println(bstCalc.getHeight());
    }
}
