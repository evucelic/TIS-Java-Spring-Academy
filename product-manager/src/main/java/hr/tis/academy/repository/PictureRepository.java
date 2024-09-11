package hr.tis.academy.repository;

import hr.tis.academy.model.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    Picture findByPictureURL(String pictureURL);

    Picture findByPictureId(Long pictureID);

}
