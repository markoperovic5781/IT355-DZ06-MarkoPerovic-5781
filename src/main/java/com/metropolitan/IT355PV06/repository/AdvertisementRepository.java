package com.metropolitan.IT355PV06.repository;

import com.metropolitan.IT355PV06.entity.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

}

