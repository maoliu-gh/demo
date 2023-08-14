package com.example.demo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.util.IOUtils;
import com.example.demo.model.FileMetaData;
import com.example.demo.photo.model.PhotoStat;
import com.example.demo.repository.PhotoStatRepository;
import com.example.demo.service.PhotoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = {"photo S3 controller"})
@CrossOrigin(origins = "*")

public class PhotoS3Controller {
	private final PhotoService photoS3Service;
	
	@Autowired
	public PhotoS3Controller(PhotoService photoS3Service) {
		this.photoS3Service = photoS3Service;
	}
	
	@GetMapping (value = "/getphoto/{fileName}")
	@ResponseBody
	@ApiOperation(value = "Get photo by file name")
	public ResponseEntity<StreamingResponseBody> getPhoto( @ApiParam ("File Name") @PathVariable String fileName ) throws IOException {
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    headers.add("Content-Disposition", "attachment; filename="+fileName);
	    return new ResponseEntity<StreamingResponseBody> (new StreamingResponseBody() {
	    	@Override 
	    	public void writeTo(OutputStream outputStream) throws IOException {
	    		InputStream input = photoS3Service.downloadPhoto(fileName);
	    		IOUtils.copy(input, outputStream);
	    		input.close();
	    		outputStream.close();
	    	}
	    	},
	    headers, HttpStatus.OK);
	   
	    }
		
	@PostMapping (value = "/uploadphoto")
	@ApiOperation (value = "Upload Photo", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> uploadPhoto (@RequestPart MultipartFile file, @RequestPart FileMetaData fileData)throws IOException {
		photoS3Service.uploadPhoto(file.getOriginalFilename(), file.getInputStream(), fileData);//(@ApiParam (value = "File Name") @RequestBody MultipartFile file) 
		return new ResponseEntity<String>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
	}
	
	@GetMapping (value = "/getPhotoList")
	@ApiOperation(value = "Get photo list", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<S3ObjectSummary> getPhotoList(){

		return photoS3Service.getList(); 
	}
	
	@DeleteMapping (value = "/deletePhoto/{fileName}")
	@ApiOperation(value = "Delete a photo")
	public ResponseEntity<String> deletePhoto( @ApiParam ("File Name") @PathVariable String fileName ){
			photoS3Service.deletePhoto(fileName);
		return new ResponseEntity<String>(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK);
	}
	
	@GetMapping (value = "/getFilemMetaData/{fileKey}")
	@ApiOperation(value = "Get file metadata", produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, String> getFileMetaData(@ApiParam ("File Key") @PathVariable String fileKey ){

		return photoS3Service.getFileMetaData(fileKey);
	}
}
