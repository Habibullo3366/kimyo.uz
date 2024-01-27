package com.company.kimyouz.Unit.Order;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.entity.Orders;
import com.company.kimyouz.repository.OrdersRepository;
import com.company.kimyouz.service.OrderService;
import com.company.kimyouz.service.mapper.OrdersMapper;
import com.company.kimyouz.service.validation.OrdersValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

public class TestOrderService {

    private OrdersMapper ordersMapper;
    private OrdersValidation ordersValidation;
    private OrdersRepository ordersRepository;
    private OrderService orderService;

    @BeforeEach
    public void initObject() {
        ordersMapper = mock(OrdersMapper.class);
        ordersValidation = mock(OrdersValidation.class);
        ordersRepository = mock(OrdersRepository.class);
        this.orderService = new OrderService(ordersMapper, ordersValidation, ordersRepository);
    }

    @Test
    public void testCreateOrderPositive() {
        when(ordersValidation.ordersValid(any()))
                .thenReturn(new ArrayList<>());

        when(ordersMapper.toDto(any()))
                .thenReturn(
                        ResponseOrdersDto.builder()
                                .orderId(1)
                                .totalPrice(1.0)
                                .build()
                );
        Orders orders = Orders.builder()
                .orderId(1)
                .totalPrice(1.0)
                .build();

        when(ordersMapper.toEntity(any()))
                .thenReturn(orders);

        when(this.ordersRepository.save(any()))
                .thenReturn(orders);

        ResponseDto<ResponseOrdersDto> response = this.orderService.createEntity(any());
        assertEquals(response.getCode(), 0, "Unknown get code value returned !");
        assertNull(response.getErrorList(), "Unknown get error list value returned !");
        assertTrue(response.isSuccess(), "Unknown success value returned !");
        assertNotNull(response.getContent(), "Unknown content value returned !");

        verify(this.ordersMapper, times(1)).toDto(any());
        verify(this.ordersMapper, times(1)).toEntity(any());
        verify(this.ordersRepository, times(1)).save(any());
        verify(this.ordersValidation, times(1)).ordersValid(any());
    }

    @Test
    public void testCreateOrderException() {

        when(this.ordersValidation.ordersValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.ordersRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseOrdersDto> response = this.orderService.createEntity(any());
        assertEquals(response.getCode(), -2, "Unknown get code value returned");
        assertNull(response.getErrorList(), "Unknown get error list value returned");
        assertFalse(response.isSuccess(), "Unknown success value returned");
        assertNull(response.getContent(), "Unknown content value returned");
    }

    @Test
    public void testCreateOrderValidation() {
        when(this.ordersValidation.ordersValid(any()))
                .thenReturn(List.of(
                                new ErrorDto("userId", String.format("User with %d id is not found", 1))
                        )
                );

        ResponseDto<ResponseOrdersDto> response = this.orderService.createEntity(any());

        assertEquals(response.getCode(), -3, "Unknown get code value returned!");
        assertNotNull(response.getErrorList(), "Unknown get error list value returned!");
        assertFalse(response.isSuccess(), "Unknown success value returned!");
        assertNull(response.getContent(), "Unknown content value returned!");

        verify(this.ordersValidation, times(1)).ordersValid(any());
    }

    @Test
    public void testGetOrderPositive() {

        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.builder()
                .orderId(1)
                .totalPrice(1.0)
                .build();

        when(this.ordersMapper.toDto(any()))
                .thenReturn(responseOrdersDto);

        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Orders.builder()
                                .orderId(1)
                                .totalPrice(1.0)
                                .build()
                ));

        ResponseDto<ResponseOrdersDto> response = this.orderService.getEntity(any());

        assertTrue(response.isSuccess(), "Unknown success value returned!");
        assertNotNull(response.getMessage(), "Unknown message value returned!");
        assertEquals(response.getCode(), 0, "Unknown get code value returned!");
        assertNotNull(response.getContent(), "Unknown content value returned!");
        assertNull(response.getErrorList(), "Unknown get error list returned!");
    }

    @Test
    public void testGetOrderNegative() {

        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseOrdersDto> response = this.orderService.getEntity(any());

        assertNull(response.getContent(), "Unknown content value returned!");
        assertNull(response.getErrorList(), "Unknown get error list returned!");
        assertFalse(response.isSuccess(), "Unknown success value returned!");
        assertEquals(response.getMessage(), "Order with null:id is not found!");
        assertEquals(response.getCode(), -1, "Unknown get code value returned!");
    }

    @Test
    public void testUpdateOrderPositive() {
        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.builder()
                .orderId(1)
                .totalPrice(1.0)
                .build();

        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Orders.builder()
                                .orderId(1)
                                .totalPrice(1.0)
                                .build()
                ));
        when(this.ordersMapper.updateOrders(any(), any()))
                .thenReturn(Orders.builder()
                        .orderId(1)
                        .totalPrice(1.0)
                        .build());

        when(this.ordersMapper.toDto(any()))
                .thenReturn(responseOrdersDto);

        ResponseDto<ResponseOrdersDto> response=this.orderService.updateEntity(any(), RequestOrdersDto.builder()
                .totalPrice(1.0)
                .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(),0);
        assertEquals(response.getContent().getOrderId(),1);

        verify(this.ordersMapper,times(1)).toDto(any());
        verify(this.ordersMapper,times(1)).updateOrders(any(),any());
        verify(this.ordersRepository,times(1)).save(any());
        verify(this.ordersRepository,times(1)).findByOrderIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testUpdateNegative(){
        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseOrdersDto> response=this.orderService.updateEntity(1,any());

        assertNull(response.getContent());
        assertFalse(response.isSuccess());
        assertEquals(response.getCode(),-1);
        assertEquals(response.getMessage(), "Order with 1:id is not found!");

        verify(this.ordersRepository, times(1)).findByOrderIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testUpdateException(){
        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseOrdersDto> response=this.orderService.updateEntity(1,any());

        assertNull(response.getContent());
        assertNull(response.getErrorList());
        assertFalse(response.isSuccess());
        assertEquals(response.getCode(),-4);

        verify(this.ordersRepository, times(1)).findByOrderIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testDeletePositive(){
        ResponseOrdersDto responseOrdersDto = ResponseOrdersDto.builder()
                .orderId(1)
                .totalPrice(1.0)
                .build();

        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        Orders.builder()
                                .orderId(1)
                                .totalPrice(1.0)
                                .build()
                ));
        when(this.ordersMapper.toDto(any()))
                .thenReturn(responseOrdersDto);

        ResponseDto<ResponseOrdersDto> response=this.orderService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertNotNull(response.getContent());
        assertEquals(response.getCode(),0);
        assertEquals(response.getMessage(), "OK");

        verify(this.ordersRepository, times(1)).findByOrderIdAndDeletedAtIsNull(any());
        verify(this.ordersMapper,times(1)).toDto(any());
    }

    @Test
    public void testDeleteNegative(){
        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseOrdersDto> response=this.orderService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getContent());
        assertEquals(response.getCode(),-1);
        assertEquals(response.getMessage(), "Order with null:id is not found!");

        verify(this.ordersRepository,times(1)).findByOrderIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testDeleteException(){
        when(this.ordersRepository.findByOrderIdAndDeletedAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseOrdersDto> response=this.orderService.deleteEntity(any());

        assertNull(response.getContent());
        assertFalse(response.isSuccess());
        assertEquals(response.getCode(),-4);

        verify(this.ordersRepository, times(1)).findByOrderIdAndDeletedAtIsNull(any());
    }
}
