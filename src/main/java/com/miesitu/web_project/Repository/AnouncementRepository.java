package com.miesitu.web_project.Repository;

import java.util.Optional;

import com.miesitu.web_project.entity.Anouncement;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface AnouncementRepository extends JpaRepository<Anouncement, Long>{

    Long countByAnouncementId(long anouncementId);
    Optional<Anouncement> findByMessage(String message);

    // Page<Anouncement> findPaginated(int pageNo, int pageSize);


}
