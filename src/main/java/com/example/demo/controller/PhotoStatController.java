/*package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.photo.model.PhotoStat;
import com.example.demo.repository.PhotoStatRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = {"photo stats controller"}, description = "Photo Stat")
@CrossOrigin(origins = "*")

public class PhotoStatController {
	private final PhotoStatRepository photoStatRepo;
	
	@Autowired
	public PhotoStatController(PhotoStatRepository photoStatRepo) {
		this.photoStatRepo = photoStatRepo;
	}
	
	@GetMapping (value = "/getphotostatbyid/{photoid}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get photostatbyid")
	public PhotoStat getPhotoStatById(@PathVariable ("photoid") @ApiParam (value = "photo id") String photoid ) {

		return photoStatRepo.findOne(photoid);
	}
	
	@GetMapping (value = "/getphotostatbyid2/{photoid2}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get photostatbyid2")
	public PhotoStat getPhotoStatById2(@PathVariable ("photoid2") @ApiParam (value = "photo id2") String photoid2 ) {

		return photoStatRepo.findById2(photoid2);
	}
	
	
	@GetMapping (value = "/getphotostatbynameanduser/{name}/{user}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "Get Photo Stat By Name And User")
	public PhotoStat getPhotoStatByNameAndUser(@PathVariable ("name") @ApiParam (value = "name") String name, 
			@PathVariable ("user") @ApiParam (value = "user") String user) {
		return photoStatRepo.findByNameAndUser(name, user);
	}

	@PostMapping (value = "/savephotostat", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation (value = "Save photostat")
	public PhotoStat savePhotoStat(@ApiParam (value = "photo stat") @RequestBody PhotoStat photostat) {
		return photoStatRepo.save(photostat);
	}


	
	
	

}
*/