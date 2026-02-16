package com.rasthrabhasha.region;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.region.dto.RegionDTO;
import com.rasthrabhasha.region.dto.RegionFilterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @PostMapping("/addregion")
    public ResponseEntity<RegionDTO> addRegion(@RequestBody Map<String, Object> payload) {
        return createRegion(payload);
    }

    @PostMapping("/regions")
    public ResponseEntity<RegionDTO> createRegion(@RequestBody Map<String, Object> payload) {

        String name = (String) payload.get("regionName");
        if (name == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Region region = new Region();
        region.setRegionName(name);
        RegionDTO savedRegion = regionService.addRegion(region);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRegion);
    }

    @GetMapping("/getRegions")
    public ResponseEntity<List<RegionDTO>> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegionsDTOs());
    }

    @GetMapping("/regions")
    public Page<RegionDTO> searchRegions(
            RegionFilterDTO filter,
            Pageable pageable) {
        return regionService.searchRegions(filter, pageable);
    }

}
