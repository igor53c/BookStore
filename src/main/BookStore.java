package main;

import java.util.*;

public class BookStore {
    private static final double PRICE_PER_BOOK = 8.0;
    private static final Map<Integer, Double> DISCOUNTS = Map.of(
            1, 0.0, 2, 0.05, 3, 0.10, 4, 0.20, 5, 0.25
    );

    public double calculateBasketCost(final List<Integer> books) {
        if (books == null || books.isEmpty()) {
            return 0.0;
        }

        List<Integer> remainingBooks = new ArrayList<>(books);
        return findMinimumCost(new ArrayList<>(), remainingBooks);
    }

    private double findMinimumCost(List<Integer> currentSet, List<Integer> remainingBooks) {
        if (remainingBooks.isEmpty()) {
            return calculateCostForSets(currentSet);
        }

        double minCost = Double.MAX_VALUE;
        Set<Integer> uniqueBooks = new HashSet<>(remainingBooks);
        for (int i = 1; i <= uniqueBooks.size(); i++) {
            List<Integer> nextSet = new ArrayList<>(currentSet);
            nextSet.add(i);
            List<Integer> nextRemaining = new ArrayList<>(remainingBooks);
            removeBooks(nextRemaining, i);
            double cost = findMinimumCost(nextSet, nextRemaining);
            minCost = Math.min(minCost, cost);
        }
        return minCost;
    }

    private void removeBooks(List<Integer> books, int count) {
        Set<Integer> seen = new HashSet<>();
        Iterator<Integer> it = books.iterator();
        while (it.hasNext() && count > 0) {
            Integer book = it.next();
            if (seen.add(book)) {
                it.remove();
                count--;
            }
        }
    }

    private double calculateCostForSets(List<Integer> sets) {
        double totalCost = 0.0;
        for (Integer setSize : sets) {
            double discount = DISCOUNTS.getOrDefault(setSize, 0.0);
            totalCost += setSize * PRICE_PER_BOOK * (1 - discount);
        }
        return totalCost;
    }
}
