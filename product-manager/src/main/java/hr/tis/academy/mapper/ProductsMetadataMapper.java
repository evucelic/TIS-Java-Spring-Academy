package hr.tis.academy.mapper;

import hr.tis.academy.dto.ProductsMetadataDto;
import hr.tis.academy.model.ProductsMetadata;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductsMetadataMapper {
    ProductsMetadataMapper INSTANCE = Mappers.getMapper(ProductsMetadataMapper.class);
    ProductsMetadataDto toDto(ProductsMetadata productsMetadata);
}
