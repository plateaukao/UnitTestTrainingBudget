package com.odde.delegateverify;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.function.Consumer;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    SystemLogger systemLogger = mock(SystemLogger.class);
    
    @Test
    public void exist_order_should_update() {
        Order order = new Order() {{
            setId(91);
            setAmount(100);
        }};

        OrderModel model = mock(OrderModel.class);
        OrderController orderController = new OrderController(model, systemLogger);

        doAnswer(invocation -> {
            Consumer updateCallback = invocation.getArgument(2);
            updateCallback.accept(order);
            return null;
        }).when(model).save(any(Order.class), any(Consumer.class), any(Consumer.class));

        orderController.save(order);

        ArgumentCaptor<String> captor = forClass(String.class);
        verify(systemLogger).log(captor.capture());
        assertThat(captor.getValue()).contains("update", "91", "100");
    }

    @Test
    public void no_exist_order_should_insert() {
        //TODO
        OrderModel model = new OrderModel() {
            @Override
            public void save(Order order, Consumer<Order> insertCallback, Consumer<Order> updateCallback) {
                insertCallback.accept(order);
            }

            @Override
            public void delete(Predicate<Order> predicate) {

            }
        };

        OrderController orderController = new OrderController(model, systemLogger);

        orderController.save(new Order(){{
            setId(91);
            setAmount(100);
        }});

        ArgumentCaptor<String> captor = forClass(String.class);
        verify(systemLogger).log(captor.capture());
        assertThat(captor.getValue()).contains("insert", "91", "100");
    }

    @Test
    public void verify_lambda_function_of_delete() {
        //TODO
        OrderModel model = mock(OrderModel.class);
        OrderController orderController = new OrderController(model, systemLogger);

        orderController.deleteAmountMoreThan100();

        ArgumentCaptor<Predicate> captor = forClass(Predicate.class);
        verify(model).delete(captor.capture());
        assertThat(captor.getValue().test(new Order() {{
            setAmount(99);
        }})).isFalse();
        assertThat(captor.getValue().test(new Order() {{
            setAmount(101);
        }})).isTrue();

    }
}
