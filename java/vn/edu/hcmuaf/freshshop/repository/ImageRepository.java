package vn.edu.hcmuaf.freshshop.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.freshshop.model.Image;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, UUID> {
    List<Image> findByProduct_Id(UUID id);
}
