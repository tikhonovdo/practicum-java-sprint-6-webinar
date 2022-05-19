package order;

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

    @Test
    void creationOfNewOrderShouldIncrementOrderSizeTest() {
        // Дано (Given)
        OrderManager orderManager = new OrderManager();
        // Совершаемое действие (When)
        List<Item> items = List.of(
                new Item("apple", 1),
                new Item("banana", 2)
        );
        long price = 12345;
        double weight = 15.123;
        orderManager.createOrder(new Order(items, price, weight));
        // Проверки (Then)
        assertEquals(1, orderManager.getOrders().size());
    }

    @MethodSource("orderWeightsSource")
    @ParameterizedTest(name = "{index} Create order with weight={0}")
    void orderWeightValidationTest(Double weight, boolean isOrderCreated) {
        // Дано (Given)
        OrderManager orderManager = new OrderManager();
        // Совершаемое действие (When)
        List<Item> items = List.of(
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

    private Stream<Arguments> orderWeightsSource() {
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