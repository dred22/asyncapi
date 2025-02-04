package com.magomed.a.mapper;

import com.magomed.a.data.ArticleDto;
import com.magomed.a.data.OrderDto;
import com.magomed.a.entity.ArticleEntity;
import com.magomed.a.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ArtAndOrdMapper {

    @Mapping(target = "orderId", source = "order.orderId")
    ArticleDto articleEntityToDto(ArticleEntity entity);

    OrderDto orderEntityToDto(OrderEntity orderEntity);
}
