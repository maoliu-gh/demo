//package com.example.demo.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.example.demo.model.SampleModel;
//import com.example.demo.photo.model.PhotoStat;
//import com.example.demo.photo.repository.PhotoRepository;
//import com.example.demo.repository.PhotoStatRepository;
//import com.example.demo.service.PhotoService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//
//@RestController
//@Api(tags = {"my-photo-service-controller"}, description = "My Photo Service")
//@CrossOrigin(origins = "*")
//public class PhotoController{
//	private final PhotoStatRepository sampleRepo;
//	private final PhotoRepository photoRepo;
//	
//	@Autowired
//	public PhotoController(PhotoStatRepository sampleRepo, PhotoRepository photoRepo) {
//		this.sampleRepo = sampleRepo;
//		this.photoRepo = photoRepo;
//	}
//
//	
//	@GetMapping (value = "/getphoto/{photoid}")
//	@ApiOperation(value = "Get photos")
//	public String getPhoto(@PathVariable ("photoid") @ApiParam (value = "photo id") int photoid ) {
//
//		return sampleRepo.getOne(photoid).getName();
//	}
//
//	@PostMapping (value = "/savephoto")
//	@ApiOperation (value = "Save photos")
//	public SampleModel savePhoto(@ApiParam (value = "photo name") @RequestBody String name) {
//		SampleModel sample1 = new SampleModel (null, name);
//		return sampleRepo.save(sample1);
//	}
//	
//	
//	@GetMapping (value = "/getmongophoto/{photoid}")
//	@ApiOperation(value = "Get photos")
//	public PhotoStat getMongoPhoto(@PathVariable ("photoid") @ApiParam (value = "photo id") String photoid ) {
//
//		return photoRepo.findOne(photoid);
//	}
//	
//}
