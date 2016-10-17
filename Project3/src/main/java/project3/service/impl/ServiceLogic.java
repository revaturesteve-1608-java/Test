package project3.service.impl;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import project3.dto.AwsKey;
import project3.dto.Person;
import project3.dto.Role;
import project3.service.Crypt;
import project3.service.Jets3;
import project3.service.ServiceInterface;
import project3.simpledao.LoginDao;
import project3.simpledao.SimpleDao;
import project3.util.Email;
import project3.util.ProfileImage;

@Service
public class ServiceLogic implements ServiceInterface{
	
	@Autowired
	Crypt crypt;
	
	@Autowired
	Jets3 jetS3;
	
	/**
	 * Using to access the database
	 */
	@Autowired // mapped to the bean
	SimpleDao dao;
	
	@Autowired
	LoginDao loginDao;
	
	private String getRandom(int length){
		return crypt.getRandom(length);
	}
	
	private String maskElement(String target){			
		return crypt.encrypt(target);
	}
	
	private File convByteToFile(byte[] pic) throws IOException {
		//below is the different part
		ByteArrayInputStream bis = new ByteArrayInputStream(pic);
        Iterator<?> readers = ImageIO.getImageReadersByFormatName("png");
 
        //ImageIO is a class containing static methods for locating ImageReaders
        //and ImageWriters, and performing simple encoding and decoding. 
 
        ImageReader reader = (ImageReader) readers.next();
        Object source = bis; 
        ImageInputStream iis = ImageIO.createImageInputStream(source); 
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
 
        Image image = reader.read(0, param);
        //got an image file
 
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_RGB);
        //bufferedImage is the RenderedImage to be written
 
        Graphics2D g2 = bufferedImage.createGraphics();
        g2.drawImage(image, null, null);
 
        File imageFile = new File("default2.png");
        ImageIO.write(bufferedImage, "png", imageFile);
 
        System.out.println(imageFile.getPath());
		return imageFile;
	}
	
	/**
	 * Encodes the byte array into base64 string
	 *
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
	public static String encodeImage(byte[] imageByteArray) {
		return Base64.encodeBase64URLSafeString(imageByteArray);
	}
	
	// method to send an email to employee regarding reimbursement request status. 
	//Email sent by another thread
	private void email(String email, String message, String subject){
		
		class myRunnable implements Runnable {
			private String threadEmail = email;
			private String threadMessage = message;
			private String threadSubject = subject;
			@Override
			public void run() {
				Email.sendEmail(threadEmail, threadMessage, threadSubject);
			}
		}
		Thread t = new Thread(new myRunnable());
		t.start();
	}

	@Override
	public String createUser(Person person) {
		person.setEmail(person.getEmail().toLowerCase());
		// Check if email exists
		if(dao.getPersonByEmail(person.getEmail()) == null) {
			//make new user name 
			person.setUsername(person.getEmail());
			System.out.println("password: " + person.getPassword());
			//set it as new user
			person.setVaildated(false);
			// Generate a Temporary Password
			String password = getRandom(20);
			System.out.println("password2: " + password);
			person.setPassword(maskElement(password));
			System.out.println("password3: " + person.getPassword());
			
//			person.setProfilePic(ProfileImage.getGravatar80pxByte(person.getEmail()));
//			System.out.println("profile pic: " + person.getProfilePic());
//			if(person.getProfilePic() == null) {
//				person.setProfilePic(ProfileImage.getGravatar80pxByte
//						("revature.reimbursements@gmail.com"));
				// Converting Image byte array into Base64 String
//				String imageDataString = encodeImage(person.getProfilePic());
//				System.out.println("pics: " + imageDataString);
//				FileOutputStream imageOutFile;
//				try {
//					File currentDirFile = new File(".");
//					String helper = currentDirFile.getAbsolutePath();
//					System.out.println(helper);
//					//convert array of bytes into file
//				    FileOutputStream fileOuputStream =
//			                  new FileOutputStream("testing2.png");
//				    fileOuputStream.write(person.getProfilePic());
//				    fileOuputStream.close();
////					imageOutFile = new FileOutputStream("./water-drop-after-convert.png");
//////					imageOutFile.write(person.getProfilePic());
////					//convert byte array to file
////					imageOutFile.
////					imageOutFile.close();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				
				
//				try {
//					System.out.println(jetS3.uploadProfileItem(person.getEmail(), "profile.png", convByteToFile(person.getProfilePic())));
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			//not done
//			} else {
//				person.setProfilePic(ProfileImage.getGravatar80pxByte
//						(person.getEmail()));
//			}
			// Save in Database
			Person user = dao.createUser(person);
			// set a default profile picture
			AwsKey key = dao.getAWSKey();
			person.setProfilePic(jetS3.uploadProfileItem(user.getId() + "", user.getId() + "", 
					key.getAccessKey(), key.getSecretAccessKey()));
			dao.updatePersonPic(person);
			// Send Email to Account
			String subject = "Welcome to Revature";
			String message = 
			"<table style=\"min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
			    + "<tbody>"
					+ "<tr>"
						+ "<td style=\"min-width:100%;padding:1px 18px;\">"
							+ "<table style=\"min-width:100%;border-top-width:5px;border-top-style:solid;border-top-color:#F26925;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">"
								+ "<tbody>"
									+ "<tr>"
			                        	+ "<td>"
			                            	+ "<span></span>"
			                            + "</td>"
			                        + "</tr>"
			                    + "</tbody>"
			                + "</table>"
			            + "</td>"
			        + "</tr>"
			    + "</tbody>"
			+ "</table>"
			+ "<h1>Welcome to RevPages !!</h1>"
			+ "<table style=\"overflow-x:auto;max-width:100%;min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">"
            	+ "<tbody>"
            		+ "<tr>"
            			+ "<td style=\"padding-top:0;padding-right:18px;padding-bottom:9px;padding-left:18px;\" valign=\"top\">"
            				+ "<h3 style=\"text-align:left;\">Hello " + person.getFirst_name() + " " + person.getLast_name() + "</h3>"
            				+ "<p style=\"text-align:left;\"> Your account had been approve<br><br>"
            				
            				+ "Here is your login information <br><br>" 
        					+ "Temporary Username: " + person.getUsername() + "<br>" 
        					+ "Temporary Password: " + password
        					+ "<br>"
        					+ "<br>"
        					+ "Click the link below to login: "
        					+ "<a rel=\"nofollow\" target=\"_blank\" href=\"http://ec2-54-152-99-76.compute-1.amazonaws.com:8080/Project3/\">Login</a></p>"

            				+ "<br>"
            				+ "<br>"
            				+ "<br>Regards<br>"
            				+ "<strong>RevPages</strong><br>"
            				+ "<a rel=\"nofollow\" target=\"_blank\" href=\"https://revature.com/\">https://www.revature.com</a></p>"

            			+ "</td>"
            		+ "</tr>"
            	+ "</tbody>"
            + "</table>";
			email(person.getEmail(), message, subject);
			return "[\"User had been created\"]";
		} else {
			//tell that the email already exist
			System.out.println("email already exist");
			return "[\"Email already exist\"]";
		}		
	}

	@Override
	public List<Role> getRoles() {
		return dao.getRoles();
	}

	@Override
	public Person loginUser(String username, String password) {
		Person person = dao.getPersonByUsername(username);
		if(person != null && crypt.validate(password, person.getPassword())) {
			return person;
		}
		return null;
	}

	@Override
	public Person updateProfilePic(Person person, MultipartFile picture) {
		AwsKey key = dao.getAWSKey();
		person.setProfilePic(jetS3.uploadProfileItem(person.getId() + "", person.getId() + "", picture,
				key.getAccessKey(), key.getSecretAccessKey()));
		dao.updatePersonPic(person);
		return person;
	}

	@Override
	public Person getPersonByUsername(String username) {
		return dao.getPersonByUsername(username);
	}
	

}
