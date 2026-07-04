package com.rasthrabhasha.region;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.rasthrabhasha.common.enums.Permission;
import com.rasthrabhasha.common.util.PermissionUtils;
import com.rasthrabhasha.common.dto.PageResponse;
import com.rasthrabhasha.region.dto.RegionDTO;
import com.rasthrabhasha.region.dto.RegionFilterDTO;

import org.springframework.data.domain.Pageable;

@RestController
public class RegionController {

    @Autowired
    RegionService regionService;

    @PostMapping("/addregion")
    public ResponseEntity<?> addRegion(@RequestBody Map<String, Object> payload) {
        return createRegion(payload);
    }

    @PostMapping("/regions")
    public ResponseEntity<?> createRegion(@RequestBody Map<String, Object> payload) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_REGIONS);
        if (err != null) return err;
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
    public ResponseEntity<?> getAllRegions() {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_REGIONS);
        if (err != null) return err;
        return ResponseEntity.ok(regionService.getAllRegionsDTOs());
    }

    @GetMapping("/regions")
    public ResponseEntity<?> searchRegions(
            RegionFilterDTO filter,
            Pageable pageable) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.VIEW_REGIONS);
        if (err != null) return err;
        return ResponseEntity.ok(regionService.searchRegions(filter, pageable));
    }

    @PutMapping("/regions/{id}")
    public ResponseEntity<?> updateRegionv1(@PathVariable long id, @RequestBody Region region) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_REGIONS);
        if (err != null) return err;
        return ResponseEntity.ok(regionService.updateRegion(id, region));
    }

    @DeleteMapping("/regions/{id}")
    public ResponseEntity<?> deleteRegion(@PathVariable long id) {
        ResponseEntity<?> err = PermissionUtils.checkPermission(Permission.MANAGE_REGIONS);
        if (err != null) return err;
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}
