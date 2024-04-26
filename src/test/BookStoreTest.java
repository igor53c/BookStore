package test;

import static org.junit.Assert.assertEquals;

import main.BookStore;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BookStoreTest {

    private final BookStore bookstore = new BookStore();

    // Scenario 1: Regular Prices
    @Test
    public void testEmptyBasket() {
        assertEquals("An empty basket should cost 0.0 EUR.", 0.0,
                bookstore.calculateBasketCost(Collections.emptyList()), 0.001);
    }

    @Test
    public void testOneCopyOfABook() {
        assertEquals("One book should cost 8.0 EUR.", 8.0,
                bookstore.calculateBasketCost(List.of(1)), 0.001);
    }

    @Test
    public void testTwoCopiesOfTheSameBook() {
        assertEquals("Two copies of the same book should cost 16.0 EUR.", 16.0,
                bookstore.calculateBasketCost(Arrays.asList(2, 2)), 0.001);
    }

    // Scenario 2: Discount on Different Books
    @Test
    public void testTwoDifferentBooks() {
        assertEquals("Two different books with a 5% discount should cost 15.20 EUR.", 15.20,
                bookstore.calculateBasketCost(Arrays.asList(1, 2)), 0.001);
    }

    @Test
    public void testThreeDifferentBooks() {
        assertEquals("Three different books with a 10% discount should cost 21.60 EUR.", 21.60,
                bookstore.calculateBasketCost(Arrays.asList(1, 2, 3)), 0.001);
    }

    @Test
    public void testFourDifferentBooks() {
        assertEquals("Four different books with a 20% discount should cost 25.60 EUR.", 25.60,
                bookstore.calculateBasketCost(Arrays.asList(1, 2, 3, 4)), 0.001);
    }

    @Test
    public void testFiveDifferentBooks() {
        assertEquals("Five different books with a 25% discount should cost 30.00 EUR.", 30.00,
                bookstore.calculateBasketCost(Arrays.asList(1, 2, 3, 4, 5)), 0.001);
    }

    // Scenario 3: Optimizing Book Grouping for Maximum Discounts
    @Test
    public void testComplexBasketGrouping_4plus4() {
        assertEquals("A complex basket with two groups of four books each with a 20% discount should cost 51.20 EUR.",
                51.20, bookstore.calculateBasketCost(Arrays.asList(1, 1, 2, 2, 3, 3, 4, 5)), 0.001);
    }
}
