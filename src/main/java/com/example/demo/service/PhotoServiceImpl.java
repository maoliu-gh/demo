package com.example.demo.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CompleteMultipartUploadRequest;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadRequest;
import com.amazonaws.services.s3.model.InitiateMultipartUploadResult;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PartETag;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.model.UploadPartRequest;
import com.amazonaws.services.s3.model.UploadPartResult;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;
import com.amazonaws.services.s3.transfer.Upload;
import com.example.demo.model.FileMetaData;
import com.example.demo.model.SampleModel;
import com.example.demo.photo.model.PhotoStat;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PhotoServiceImpl implements PhotoService {

	private final AmazonS3 s3Client;
	private final String bucketName;
	
	@Autowired 
	public PhotoServiceImpl(AmazonS3 s3Client, @Value("${com.kp.bucket}")String bucketName) {
		this.s3Client = s3Client;
		this.bucketName = bucketName;
	}

	@Override
	public List<S3ObjectSummary> getList() {
		ObjectListing objectListing = s3Client.listObjects(bucketName);
		return objectListing.getObjectSummaries();
	}

	@Override
	public void uploadPhoto(String fileName, InputStream input, FileMetaData fileData) throws IOException {
		 //   s3client.putObject(new PutObjectRequest(bucketName, fileName, input, null));
	       // Set part size to 5 MB. 

	        try {
	            // Create a list of ETag objects. You retrieve ETags for each object part uploaded,
	            // then, after each individual part has been uploaded, pass the list of ETags to 
	            // the request to complete the upload.
	            List<PartETag> partETags = new ArrayList<PartETag>();
	            // Initiate the multipart upload.
	    //        InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, fileName);
	            ObjectMetadata metaData = new ObjectMetadata();
	            ObjectMapper mapper = new ObjectMapper();
	           Map<String, String> dataMap = mapper.convertValue(fileData, new TypeReference<Map<String, String>>(){});
	            metaData.setUserMetadata(dataMap);
	            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucketName, fileName, metaData);
	            InitiateMultipartUploadResult initResponse = s3Client.initiateMultipartUpload(initRequest);

	            // Upload the file parts.
	            int partCounter = 0;
	            
	            int read; 
	            byte[] data = new byte [5242880];
	            while ((read = IOUtils.read(input, data))>0) {
	            	partCounter ++;
	            	 ByteArrayOutputStream out = new ByteArrayOutputStream();
	            	 out.write(data, 0, read);
	            	 out.flush();
	            	 byte[] part =out.toByteArray();
	            	 out.close();
	            	 ByteArrayInputStream inputStream = new ByteArrayInputStream(part);	                // Because the last part could be less than 5 MB, adjust the part size as needed.
	                // Create the request to upload a part.
	                UploadPartRequest uploadRequest = new UploadPartRequest()
	                        .withBucketName(bucketName)
	                        .withKey(fileName)
	                        .withUploadId(initResponse.getUploadId())
	                        .withPartNumber(partCounter)
	                        .withInputStream(inputStream)
	                        .withPartSize(read);

	                // Upload the part and add the response's ETag to our list.
	                UploadPartResult uploadResult = s3Client.uploadPart(uploadRequest);
	                partETags.add(uploadResult.getPartETag());
	            }

	            // Complete the multipart upload.
	            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucketName, fileName,
	                    initResponse.getUploadId(), partETags);
	            s3Client.completeMultipartUpload(compRequest);
	        }
	        catch(AmazonServiceException e) {
	            // The call was transmitted successfully, but Amazon S3 couldn't process 
	            // it, so it returned an error response.
	            e.printStackTrace();
	        }
	        catch(SdkClientException e) {
	            // Amazon S3 couldn't be contacted for a response, or the client
	            // couldn't parse the response from Amazon S3.
	            e.printStackTrace();
	        }
	    }

	@Override
	public InputStream downloadPhoto(String fileName) {
		S3Object s3object = s3Client.getObject(bucketName, fileName);
		return s3object.getObjectContent();
	}
	
	@Override
	public void deletePhoto(String fileName) {
		s3Client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
	}

	@Override
	public Map<String, String> getFileMetaData(String key) {
		 
		ObjectMetadata objectMetadata = s3Client.getObjectMetadata(bucketName, key); 
		  return objectMetadata.getUserMetadata();
	}
	
    
}
