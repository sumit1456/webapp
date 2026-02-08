package com.rasthrabhasha.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SchoolController {
	
	
	@Autowired
	SchoolService schoolService;
	
	
	 @PostMapping("/addSchool")
	    public ResponseEntity<School> addSchool(
	            @RequestParam Long centreId,
	            @RequestBody School school) {

	        School savedSchool = schoolService.addSchool(centreId, school);

	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(savedSchool);
	 }
	 
	 
	 @GetMapping("getAllSchools")
	 public ResponseEntity<List<School>> getAllSchools(){
		List <School> list = schoolService.getAllSchools();
		return ResponseEntity.ok(list);
		 
	 }
}
