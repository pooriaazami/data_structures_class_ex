package com.yazduni.Quera;


import java.util.*;

public class ArrayReshape {

    private boolean isPrime(int number) {
        int len = (int) Math.sqrt(number) + 1;
        for (int i = 2; i < len; i++)
            if (number % i == 0)
                return false;
        return true;
    }

    private ArrayList<Integer> analyze(int number) {
        ArrayList<Integer> ans = new ArrayList<>();

        int factor = 2;
        while (number != 1) {
            if (number % factor == 0) {
                ans.add(factor);
                number /= factor;
            } else
                factor++;
        }

        return ans;
    }

    private int product(ArrayList<Integer> factors, int split) {
        int ans = 1;
        for (int i = 0; i < split; i++)
            ans *= factors.get(i);

        return ans;
    }

    private ArrayList<ArrayList<Integer>> generateArraySize(ArrayList<Integer> factors, int number, int count) {
        ArrayList<ArrayList<Integer>> shapes = new ArrayList<>();

        ArrayList<Integer> numberList = new ArrayList<>();
        numberList.add(number);
        shapes.add(numberList);

        Random random = new Random();
        Collections.shuffle(factors);

        int split;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < count; i++) {
            ArrayList<Integer> item = new ArrayList<>();
            do {
                split = random.nextInt(factors.size() - 2) + 1;
            } while (set.contains(split));
            set.add(split);
            int mid = product(factors, split);

            item.add(mid);
            item.add(number / mid);

            shapes.add(item);
        }


        return shapes;
    }

    public int generateLength() {
        Random random = new Random();
        int ans;
        do {
            ans = random.nextInt(1000000);
        } while (isPrime(ans));

        return ans;
    }

    public ArrayList<ArrayList<Integer>> generateShapes() {
        int length;
        ArrayList<Integer> factors;

        do {
            length = generateLength();
            factors = analyze(length);
        } while (factors.size() < 4);

        return generateArraySize(factors, length, 2);
    }

    public ArrayList<Integer> generateRandomArray(int length) {
        ArrayList<Integer> array = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < length; i++)
            array.add(random.nextInt(5000000));

        return array;
    }
}
