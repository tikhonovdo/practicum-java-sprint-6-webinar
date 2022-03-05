package order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderManagerTest {

    @DisplayName("GIVEN a new instance or OrderManager " +
            "WHEN a new order is created " +
            "THEN number of saved orders is 1")
    @Test
    void test1_creationOfNewOrderShouldIncrementOrderSize() {
        // Дано (Given)
        OrderManager orderManager = new OrderManager();
        // Совершаемое действие (When)
        List<Item> items =
                List.of(
                        new Item("apple", 1),
                        new Item("banana", 2)
                );
        long price = 12345;
        double weight = 15.123;
        orderManager.createOrder(new Order(items, price, weight));
        // Проверки (Then)
        assertEquals(1, orderManager.getOrders().size());
    }

    @DisplayName("GIVEN a new instance or OrderManager " +
            "WHEN a new order is created " +
            "THEN order creation method returns " +
            "true if weight is allowed")
    @MethodSource("test2MethodSource")
    @ParameterizedTest(name = "{index} Create order with weight={0}")
    void test2_orderWeightValidation(Double weight,
                                     boolean isOrderCreated) {
        // Дано (Given)
        OrderManager orderManager = new OrderManager();
        // Совершаемое действие (When)
        List<Item> items =
                List.of(
                        new Item("apple", 1),
                        new Item("banana", 2)
                );
        long price = 12345;
        boolean orderCreationResult =
                orderManager.createOrder(
                        new Order(items, price, weight));
        // Проверки (Then)
        assertEquals(isOrderCreated, orderCreationResult);
    }

    private Stream<Arguments> test2MethodSource() {
        return Stream.of(
                Arguments.of(-1.0, false),
                Arguments.of(-0.001, false),
                Arguments.of(0.0, false),
                Arguments.of(0.001, true),
                Arguments.of(5.0, true),
                Arguments.of(19.999, true),
                Arguments.of(20.0, true),
                Arguments.of(20.001, false),
                Arguments.of(25.0, false)
        );
    }
}