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



@RestController
public class RegionController {
	
	
	@Autowired
	RegionService regionService;
	

@PostMapping("/addregion")
public ResponseEntity<?> addRegion(@RequestBody Map<String, Object> payload) {
    // 1. Log the RAW map to see what Spring actually received
    System.out.println("RAW PAYLOAD RECEIVED: " + payload);
   
    // 2. Manually extract the value
    String name = (String) payload.get("regionName");
    System.out.println("EXTRACTED NAME: " + name);
    if (name == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body("Error: regionName is missing in JSON!");
    }
    // 3. Create and save the Region object manually
    Region region = new Region();
    region.setRegionName(name);
    Region savedRegion = regionService.addRegion(region);
    System.out.println("This is a saved Exam Centre entity");
    System.out.println(savedRegion);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedRegion);
}
	
	@GetMapping("/getRegions")
	public ResponseEntity<List<Region>> getAllRegions() {
	    List<Region> regions = regionService.getAllRegions();
	    return ResponseEntity.ok(regions);
	}
	

}
