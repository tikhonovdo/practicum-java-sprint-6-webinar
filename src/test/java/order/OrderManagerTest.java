package order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class OrderManagerTest {

    @Test
    @DisplayName("Order creation should increment orders size")
    public void orderCreationShouldIncrementOrdersSize() {
        // Given (дано)
        OrderManager orderManager = new OrderManager();
        Order order = new Order(List.of(
                new Item("banana", 3),
                new Item("pineapple", 1)
        ), 500, 3.21);

        // When (действие)
        orderManager.createOrder(order);

        // Then (проверки)
        assertEquals(1, orderManager.getOrders().size());
    }

    @DisplayName("Order weights validation")
    @ParameterizedTest(name = "{index} Order creation result with weight={0} is {1}")
    @MethodSource("weightsStream")
    public void orderWeightsValidation(Double weight, boolean expectedResult) {
        // Given (дано)
        OrderManager orderManager = new OrderManager();
        Order order = new Order(List.of(
                new Item("banana", 3),
                new Item("pineapple", 1)
        ), 500, weight);

        // When (действие)
        boolean actualResult = orderManager.createOrder(order);

        // Then (проверки)
        assertEquals(expectedResult, actualResult);
    }


    @Test
    @DisplayName("Order cancellation should decrement orders size")
    public void orderCancellationShouldDecrementOrdersSize() {
        // Given (дано)
        OrderManager orderManager = new OrderManager();
        Order order1 = new Order(List.of(
                new Item("banana", 3),
                new Item("pineapple", 1)
        ), 500, 3.21);
        Order order2 = new Order(List.of(
                new Item("pen", 1),
                new Item("pineapple", 1)
        ), 1200, 2.01);

        // When (действие)
        orderManager.createOrder(order1);
        orderManager.createOrder(order2);
        orderManager.cancelOrder(order1.getId());

        // Then (проверки)
        assertEquals(1, orderManager.getOrders().size());
    }

    private static Stream<Arguments> weightsStream() {
        return Stream.of(
                Arguments.of(-1.0, false),
                Arguments.of(-0.01, false),
                Arguments.of(0.0, false),
                Arguments.of(0.01, true),
                Arguments.of(10.0, true),
                Arguments.of(19.99, true),
                Arguments.of(20.0, true),
                Arguments.of(20.01, false),
                Arguments.of(21.0, false)
        );
    }

}