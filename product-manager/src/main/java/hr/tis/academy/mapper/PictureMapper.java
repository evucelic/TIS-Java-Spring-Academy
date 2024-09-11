package hr.tis.academy.mapper;

import hr.tis.academy.dto.PictureDto;
import hr.tis.academy.model.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;



@Mapper(componentModel = "spring")
public interface PictureMapper {

    @Mapping(target = "pictureId", ignore = true)
    @Mapping(target = "attractionPicture", ignore = true)
    Picture toPictureEntity(PictureDto pictureDto);

    PictureDto toPictureDto(Picture pictureEntity);
}
