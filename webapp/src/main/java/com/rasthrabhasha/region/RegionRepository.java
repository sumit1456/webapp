package com.rasthrabhasha.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

	Region save(Region region);

}
