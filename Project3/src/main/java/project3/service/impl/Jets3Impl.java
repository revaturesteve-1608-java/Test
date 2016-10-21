package project3.service.impl;

import java.io.File;
import java.io.FileInputStream;

import org.jets3t.service.S3Service;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import project3.service.Jets3;

@Component
public class Jets3Impl implements Jets3{

//	private AWSCredentials credentials;
	private S3Service S3; 
//	= new RestS3Service(new AWSCredentials(
//			dao.getAWSKey().getAccessKey(), 
//			dao.getAWSKey().getSecretAccessKey()));
	private static final String BUCKET = "revaturepage";
//	private ServiceInterface businessDelegate;
	private static final String PROFILES = "profiles/";
	private static final String HTTP = "http://";
	private static final String ADDRESS = "s3-us-west-2.amazonaws.com/";

//	public void setBusinessDelegate(ServiceInterface businessDelegate) {
//		this.businessDelegate = businessDelegate;
//		syncBusinessDelegate(this.businessDelegate);
//	}

//	public synchronized void syncBusinessDelegate(ServiceInterface businessDelegate){
//		 
//	   	credentials = new AWSCredentials("AKIAJ" + "MZNDUN2" + "JNOIT5XQ","hqsTkXgW" + "7J1aQYdTY" + "bX87JyiSF7M4V7" + "HKaY8tCpF");
//	   	s3 = new RestS3Service(credentials);
//	}

	public String[] list(){
		try {
			S3Object[] storage = S3.listObjects(BUCKET);
			String[] str = new String[storage.length];
			for(int i = 0;i<storage.length;i++){
				str[i]=storage[i].getName();
			}
			return str;
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
			return new String[0];
		}
	}

	/**
	 * Attempts to upload a resource (such as a CSS or JS file) to the S3 server
	 * @param fileName the destination name of the file, a valid extension should be included
	 * @param file a file that is to be uploaded to the S3 server
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	public String uploadResource(String fileName, MultipartFile file) {
		return  uploadFile("content/resources/" + System.nanoTime() + "/", fileName, file);
	}
	
	/**
	 * Attempts to upload a front-end page (html) to the S3 server
	 * @param file a file that is to be uploaded to the database, the file should have a valid extension
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	public String uploadPage(File file) {
		return uploadFile("content/pages/", file);
	}
	
	/**
	 * Attempts to upload an 'evidence' (picture, code, attachment) to the S3 server
	 * @param fileName the destination name of the file, a valid extension should be included
	 * @param file a file that is to be uploaded to the S3 server
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	public String uploadEvidence(String fileName, MultipartFile file) {
		return uploadFile("content/evidence/"+ System.nanoTime() + "/", fileName, file);
	}
	
	/**
	 * Attempts to upload a profile picture to the S3 server
	 * @param fileName the destination name of the file, a valid extension should be included
	 * @param loginName the name (or email) the user logs in with
	 * @param file a file that is to be uploaded to the S3 server
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	public String uploadProfileItem(String loginName, String fileName, MultipartFile file) {
		return uploadFile(PROFILES + loginName + "/", fileName, file);
	}
	
	public String uploadProfileItem(String loginName, String fileName, MultipartFile file, 
			String publicKey, String privateKey) {
		return uploadFile(PROFILES + loginName + "/", fileName, file, publicKey, privateKey);
	}
	
	public String uploadProfileItem(String loginName, String fileName, File file){
		return uploadFile(PROFILES + loginName + "/", fileName, file);
	}
	
	public String uploadProfileItem(String loginName, String fileName){
		return copyDefault(PROFILES + loginName + "/", fileName);
	}
	
	public String uploadProfileItem(String loginName, String fileName, String publicKey, String privateKey){
		return copyDefault(PROFILES + loginName + "/", fileName, publicKey, privateKey);
	}
	
	public String uploadProfileItem(String loginName, File file){
		return uploadFile(PROFILES + loginName + "/", file);
	}
	
	public String uploadRemote(String folderPath, MultipartFile file){
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3Object s3Obj = new S3Object(folderPath);
			s3Obj.setContentType(file.getContentType());
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			s3Obj.setDataInputStream(file.getInputStream());
			s3Obj.setContentLength(file.getSize());
			s3Obj.setAcl(acl);
			S3.putObject(bucket, s3Obj);
			return HTTP + ADDRESS + BUCKET+ "/" + folderPath;
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	/**
	 * Attempts to upload a file to the S3 server
	 * @param folderPath the path to the folder this file will be stored at starting at the S3 root
	 * @param fileName the destination name of the file, a valid extension should be included
	 * @param file a File that is to be uploaded to the database
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	protected String uploadFile(String folderPath, String fileName, MultipartFile file) {
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3Object s3Obj = new S3Object(folderPath + fileName);
			s3Obj.setContentType(file.getContentType());
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			s3Obj.setDataInputStream(file.getInputStream());
			s3Obj.setContentLength(file.getSize());
			s3Obj.setAcl(acl);
			S3.putObject(bucket, s3Obj);

			return 
				HTTP + ADDRESS +BUCKET+ "/" + folderPath + fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	
	protected String uploadFile(String folderPath, String fileName, MultipartFile file,
			String publicKey, String privateKey) {
		S3 = new RestS3Service(new AWSCredentials(
				publicKey, privateKey));
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3Object s3Obj = new S3Object(folderPath + fileName);
			s3Obj.setContentType(file.getContentType());
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			s3Obj.setDataInputStream(file.getInputStream());
			s3Obj.setContentLength(file.getSize());
			s3Obj.setAcl(acl);
			S3.putObject(bucket, s3Obj);

			return 
				HTTP + ADDRESS +BUCKET+ "/" + folderPath + fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	
	protected String copyDefault(String folderPath, String fileName) {
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			System.out.println("bucket" + bucket);
			String bucketName = bucket.getName();
			S3Object s3Obj = new S3Object(folderPath + fileName);
			System.out.println("s3obj" + s3Obj);
			S3.copyObject(bucketName, "resources/img/default.png", bucketName, s3Obj, false);
			return 
				HTTP + ADDRESS + BUCKET + "/" + folderPath + fileName;
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	
	protected String copyDefault(String folderPath, String fileName, String publicKey, String privateKey) {
		S3 = new RestS3Service(new AWSCredentials(
				publicKey, privateKey));
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			System.out.println("bucket" + bucket);
			String bucketName = bucket.getName();
			S3Object s3Obj = new S3Object(folderPath + fileName);
			System.out.println("s3obj" + s3Obj);
			s3Obj.setAcl(AccessControlList.REST_CANNED_PUBLIC_READ);
			S3.copyObject(bucketName, "resources/img/default.png", bucketName, s3Obj, false);
			return 
				HTTP + ADDRESS + BUCKET + "/" + folderPath + fileName;
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	
	public String uploadInitial(File file) {
		return uploadFile("",file.getName(),file);
	}
	/**
	 * Attempts to upload a file to the S3 server
	 * @param folderPath the path to the folder this file will be stored at starting at the S3 root
	 * @param fileName the destination name of the file, a valid extension should be included
	 * @param file a File that is to be uploaded to the database
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	
	protected String uploadFile(String folderPath, String fileName, File file) {
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3Object s3Obj = new S3Object(folderPath + fileName);
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			FileInputStream fis = new FileInputStream(file);
			s3Obj.setDataInputStream(fis);
			s3Obj.setContentLength(file.length());
			s3Obj.setAcl(acl);
			s3Obj.setContentType("text/html");
			S3.putObject(bucket, s3Obj);
			
			return 
					HTTP + ADDRESS + BUCKET+ "/" + folderPath + fileName;
			
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	
	/**
	 * Attempts to upload a file to the S3 server
	 * @param folderPath the path to the folder this file will be stored at starting at the S3 root
	 * @param fileName the destination name of the file, a valid extension should be included
	 * @param file a File that is to be uploaded to the database
	 * @return the URL where the file was uploaded if successful, null otherwise
	 */
	protected String uploadFile(String folderPath, File file) {
		
		try {
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3Object s3Obj = new S3Object(folderPath + file.getName());
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
			FileInputStream fis = new FileInputStream(file);
			s3Obj.setDataInputStream(fis);
			s3Obj.setContentLength(file.length());
			s3Obj.setAcl(acl);
			s3Obj.setContentType("text/html");
			S3.putObject(bucket, s3Obj);
			
			return 
					HTTP + ADDRESS + BUCKET+ "/" + folderPath + file.getName();
			
		} catch (Exception e) {
			e.printStackTrace();
//			Logging.error(e);
		}
		return null; // Resource could not be uploaded
	}
	
	public boolean uploadFile(MultipartFile mFile)
	{
		try{
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3Object file = new S3Object("test/"+mFile.getOriginalFilename());
			file.setContentType(mFile.getContentType());
			AccessControlList acl = new AccessControlList();
			acl.setOwner(bucket.getOwner());
			acl.grantPermission(GroupGrantee.AUTHENTICATED_USERS, Permission.PERMISSION_READ);
			file.setDataInputStream(mFile.getInputStream());
			file.setContentLength(mFile.getSize());
			file.setAcl(acl);
			S3.putObject(bucket, file);
			}catch(Exception e)
			{
				e.printStackTrace();
//				Logging.error(e);
				return false;
			}	
			return true;
	}
	public boolean uploadText(String filename,String filedata)
	{
		try{
		S3Bucket bucket = S3.getBucket(BUCKET);
		S3Object file = new S3Object(filename, filedata);
		AccessControlList acl = new AccessControlList();
		acl.setOwner(bucket.getOwner());
		acl.grantPermission(GroupGrantee.AUTHENTICATED_USERS, Permission.PERMISSION_READ);
		file.setAcl(acl);
		S3.putObject(bucket, file);
		}catch(Exception e)
		{
			e.printStackTrace();
//			Logging.error(e);
			return false;
		}	
		return true;
	}
	public boolean delete(String filename)
	{
		try{
			S3Bucket bucket = S3.getBucket(BUCKET);
			S3.deleteObject(bucket, filename);
		}catch(Exception e)
		{
			e.printStackTrace();
//			Logging.error(e);
			return false;
		}	
		return true;
	}
}