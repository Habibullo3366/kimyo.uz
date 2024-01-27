package com.company.kimyouz.Unit.OrderItem;

import com.company.kimyouz.dto.ErrorDto;
import com.company.kimyouz.dto.ResponseDto;
import com.company.kimyouz.dto.request.RequestOrdersItemDto;
import com.company.kimyouz.dto.response.ResponseOrdersDto;
import com.company.kimyouz.dto.response.ResponseOrdersItemDto;
import com.company.kimyouz.entity.OrdersItem;
import com.company.kimyouz.repository.OrdersItemRepository;
import com.company.kimyouz.service.OrderedItemService;
import com.company.kimyouz.service.mapper.OrdersItemMapper;
import com.company.kimyouz.service.validation.OrdersItemValidation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestOrderItemService {
    private OrderedItemService orderItemService;
    private OrdersItemMapper ordersItemMapper;
    private OrdersItemValidation ordersItemValidation;
    private OrdersItemRepository ordersItemRepository;

    @BeforeEach
    public void initObject() {
        ordersItemMapper = mock(OrdersItemMapper.class);
        ordersItemRepository = mock(OrdersItemRepository.class);
        ordersItemValidation = mock(OrdersItemValidation.class);
        this.orderItemService = new OrderedItemService(ordersItemMapper, ordersItemValidation, ordersItemRepository);
    }

    @Test
    public void testCreatePositive() {
        when(ordersItemValidation.ordersItemValid(any()))
                .thenReturn(new ArrayList<>());

        when(ordersItemMapper.toDto(any()))
                .thenReturn(
                        ResponseOrdersItemDto.builder()
                                .orderId(1)
                                .totalPrice(1.0)
                                .build()
                );

        OrdersItem ordersItem = OrdersItem.builder()
                .totalPrice(1.0)
                .quantity(1.0)
                .build();

        when(ordersItemMapper.toEntity(any()))
                .thenReturn(ordersItem);
        when(ordersItemRepository.save(any()))
                .thenReturn(ordersItem);

        ResponseDto<ResponseOrdersItemDto> response = this.orderItemService.createEntity(any());
        assertEquals(response.getCode(), 0, "Unknown get code value returned !");
        assertNull(response.getErrorList(), "Unknown get error list value returned !");
        assertTrue(response.isSuccess(), "Unknown success value returned !");
        assertNotNull(response.getContent(), "Unknown content value returned !");

        verify(this.ordersItemMapper, times(1)).toDto(any());
        verify(this.ordersItemMapper, times(1)).toEntity(any());
        verify(this.ordersItemRepository, times(1)).save(any());
        verify(this.ordersItemValidation, times(1)).ordersItemValid(any());
    }

    @Test
    public void testCreateOrderException() {
        when(this.ordersItemValidation.ordersItemValid(any()))
                .thenReturn(new ArrayList<>());

        when(this.ordersItemRepository.save(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseOrdersItemDto> response = this.orderItemService.createEntity(any());
        assertEquals(response.getCode(), -2, "Unknown get code value returned");
        assertNull(response.getErrorList(), "Unknown get error list value returned");
        assertFalse(response.isSuccess(), "Unknown success value returned");
        assertNull(response.getContent(), "Unknown content value returned");
    }

    @Test
    public void testGetOrderPositive() {
        ResponseOrdersItemDto response = ResponseOrdersItemDto.builder()
                .totalPrice(1.0)
                .quantity(1.0)
                .build();

        when(this.ordersItemMapper.toDto(any()))
                .thenReturn(response);
        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        OrdersItem.builder()
                                .totalPrice(1.0)
                                .quantity(1.0)
                                .build()
                ));

        ResponseDto<ResponseOrdersItemDto> response1 = this.orderItemService.getEntity(any());

        assertTrue(response1.isSuccess(), "Unknown success value returned!");
        assertNull(response1.getErrorList(), "Unknown get error list returned!");
        assertEquals(response1.getCode(), 0, "Unknown get code value returned!");
        assertNotNull(response1.getContent(), "Unknown content value returned!");
        assertNotNull(response1.getMessage(), "Unknown message value returned!");
    }

    @Test
    public void testGetNegative() {
        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseOrdersItemDto> response1 = this.orderItemService.getEntity(any());

        assertNull(response1.getContent(), "Unknown content value returned!");
        assertNull(response1.getErrorList(), "Unknown get error list returned!");
        assertFalse(response1.isSuccess(), "Unknown success value returned!");
        assertEquals(response1.getMessage(), "Ordered item with null:id is not found!");
        assertEquals(response1.getCode(), -1, "Unknown get code value returned!");
    }

    @Test
    public void testUpdatePositive() {
        ResponseOrdersItemDto responseOrderItem = ResponseOrdersItemDto.builder()
                .totalPrice(1.0)
                .quantity(1.0)
                .build();

        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        OrdersItem.builder()
                                .totalPrice(1.0)
                                .quantity(1.0)
                                .build()
                ));
        when(this.ordersItemMapper.updateOrdersItem(any(), any()))
                .thenReturn(OrdersItem.builder()
                        .totalPrice(1.0)
                        .quantity(1.0)
                        .build());
        when(this.ordersItemMapper.toDto(any()))
                .thenReturn(responseOrderItem);

        ResponseDto<ResponseOrdersItemDto> response = this.orderItemService.updateEntity(any(), RequestOrdersItemDto.builder()
                .totalPrice(1.0)
                .quantity(1.0)
                .build());

        assertTrue(response.isSuccess());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getContent().getOrderItemId(),null);

        verify(this.ordersItemMapper, times(1)).toDto(any());
        verify(this.ordersItemMapper, times(1)).updateOrdersItem(any(),any());
        verify(this.ordersItemRepository, times(1)).save(any());
        verify(this.ordersItemRepository, times(1)).findByOrderItemIdAndDeletedAtIsNull(any());
    }
    @Test
    public void testUpdateNegative(){
        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseOrdersItemDto> response=this.orderItemService.updateEntity(1,any());

        assertNull(response.getContent());
        assertFalse(response.isSuccess());
        assertEquals(response.getCode(),-1);
        assertEquals(response.getMessage(), "Ordered item with 1:id is not found!");

        verify(this.ordersItemRepository, times(1)).findByOrderItemIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testUpdateException(){
        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseOrdersItemDto> response=this.orderItemService.updateEntity(1,any());

        assertFalse(response.isSuccess());
        assertNull(response.getContent());
        assertNull(response.getErrorList());
        assertEquals(response.getCode(), -1);

        verify(this.ordersItemRepository, times(1)).findByOrderItemIdAndDeletedAtIsNull(any());
    }

    @Test
    public void testDeletePositive(){
        ResponseOrdersItemDto responseOrdersItemDto= ResponseOrdersItemDto.builder()
                .totalPrice(1.0)
                .quantity(1.0)
                .build();

        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.of(
                        OrdersItem.builder()
                                .totalPrice(1.0)
                                .quantity(1.0)
                                .build()
                ));
        when(this.ordersItemMapper.toDto(any()))
                .thenReturn(responseOrdersItemDto);

        ResponseDto<ResponseOrdersItemDto> response=this.orderItemService.deleteEntity(any());

        assertTrue(response.isSuccess());
        assertNotNull(response.getContent());
        assertEquals(response.getCode(), 0);
        assertEquals(response.getMessage(), "OK");

        verify(this.ordersItemRepository, times(1)).findByOrderItemIdAndDeletedAtIsNull(any());
        verify(this.ordersItemMapper, times(1)).toDto(any());
    }

    @Test
    public void testDeleteNegative(){
        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenReturn(Optional.empty());

        ResponseDto<ResponseOrdersItemDto> response=this.orderItemService.deleteEntity(any());

        assertFalse(response.isSuccess());
        assertNull(response.getContent());
        assertEquals(response.getCode(), -1);
        assertEquals(response.getMessage(), "Ordered item with null:id is not found!");

        verify(this.ordersItemRepository, times(1)).findByOrderItemIdAndDeletedAtIsNull(any());
    }
    @Test
    public void testDeleteException(){
        when(this.ordersItemRepository.findByOrderItemIdAndDeletedAtIsNull(any()))
                .thenThrow(RuntimeException.class);

        ResponseDto<ResponseOrdersItemDto> response=this.orderItemService.deleteEntity(any());

        assertNull(response.getContent());
        assertFalse(response.isSuccess());
        assertEquals(response.getCode(), -4);

        verify(this.ordersItemRepository, times(1)).findByOrderItemIdAndDeletedAtIsNull(any());
    }
}
