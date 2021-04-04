import java.io.IOException;
import java.util.Scanner;

interface Stack<E> {

    void push(E data);

    E pop();

    E top();

    int size();

    int capacity();

}

interface List<E> {

    void add(E data);

    void insert(E data, int index);

    E remove(int index);

    int delete(E data);

    int size();

    int capacity();

    E get(int index);

    void set(int index, E data);

    int find(E data);

}

class DynamicArray<E> implements List<E> {

    private final int INITIAL_SIZE = 8;
    private final double GROW_RATE = 2;
    private final double SHRINK_RATE = 1 / GROW_RATE;

    private int capacity = INITIAL_SIZE;
    private int last_index = -1;

    private E[] buffer;

    private void checkIndex(int index) {
        if (index < 0 || index > last_index)
            throw new IllegalArgumentException("Invalid value for index");
    }

    public DynamicArray() {
        buffer = (E[]) new Object[INITIAL_SIZE];
    }

    private void reInitializeBuffer() {
        E[] newBuffer = (E[]) new Object[(int) (capacity)];
        System.arraycopy(buffer, 0, newBuffer, 0, size());
        buffer = newBuffer;
    }

    @Override
    public void add(E data) {
        if (last_index + 1 == capacity) {
            capacity *= GROW_RATE;
            reInitializeBuffer();
        }

        buffer[++last_index] = data;
    }

    @Override
    public void insert(E data, int index) {
        if (last_index + 1 == capacity) {
            capacity *= GROW_RATE;
            reInitializeBuffer();
        }

        last_index++;
        checkIndex(index);

        System.arraycopy(buffer, index, buffer, index + 1, last_index - index);
        buffer[index] = data;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E element = buffer[index];
        System.arraycopy(buffer, index + 1, buffer, index, last_index - index + 1);

        if (last_index + 1 == capacity * SHRINK_RATE * SHRINK_RATE && capacity * SHRINK_RATE >= INITIAL_SIZE) {
            capacity *= SHRINK_RATE;
            reInitializeBuffer();
        }

        last_index--;
        return element;
    }

    @Override
    public int delete(E data) {
        int index = find(data);

        if (index == -1)
            return -1;

        remove(index);
        return index;
    }

    @Override
    public int size() {
        return last_index + 1;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return buffer[index];
    }

    @Override
    public void set(int index, E data) {
        checkIndex(index);

        buffer[index] = data;

    }

    @Override
    public int find(E data) {
        for (int i = 0; i <= last_index; i++)
            if (buffer[i].equals(data))
                return i;

        return -1;
    }
}

class DynamicStack<E> implements Stack<E> {
    private DynamicArray<E> array;

    public DynamicStack() {
        array = new DynamicArray<>();
    }

    @Override
    public void push(E data) {
        array.add(data);
    }

    @Override
    public E pop() {
        if (array.size() == 0)
            throw new IllegalArgumentException();

        return array.remove(array.size() - 1);
    }

    @Override
    public E top() {
        if (array.size() == 0)
            throw new IllegalArgumentException();

        return array.get(array.size() - 1);
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public int capacity() {
        return array.capacity();
    }
}

class StackLogger<E> {

    private DynamicStack<E> stack;

    public StackLogger() {
        stack = new DynamicStack<>();
    }

    private void logToConsole(String res) {
        if (res.equals("ERROR\n"))
            System.out.println("ERROR");
        else if (res.equals("END"))
            System.out.println("END");
        else
            System.out.printf("%s %d %d\n", res, stack.size(), stack.capacity());
    }

    public void push(E data) {
        stack.push(data);
        logToConsole("NULL");
    }

    public void pop() {

        String res;
        if (stack.size() == 0) {
            res = "ERROR\n";
        } else {
            res = stack.pop().toString();
        }

        logToConsole(res);
    }

    public void top() {
        String res;
        if (stack.size() == 0) {
            res = "ERROR\n";
        } else {
            res = stack.top().toString();
        }

        logToConsole(res);
    }

}

public class DynamicStackQuera {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        StackLogger<Integer> stackLogger = new StackLogger<>();

        String command;
        do {
            command = input.nextLine();

            if (command.contains(" ")) {
                String[] sep = command.split(" ");
                stackLogger.push(Integer.parseInt(sep[1]));
            } else {
                switch (command) {
                    case "TOP":
                        stackLogger.top();
                        break;
                    case "POP":
                        stackLogger.pop();
                        break;
                }
            }
//
        } while (!command.equals("END"));
    }
}
