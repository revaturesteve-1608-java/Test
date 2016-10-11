package project3.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project3.dto.Person;
import project3.dto.Role;
import project3.service.Crypt;
import project3.service.ServiceInterface;
import project3.simpledao.LoginDao;
import project3.simpledao.SimpleDao;
import project3.util.Email;
import project3.util.ProfileImage;

@Component
public class ServiceLogic implements ServiceInterface{
	
	@Autowired
	Crypt crypt;
	
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
	
//	private byte[] extractBytes (String ImageName) throws IOException {
//		// open image
//		File imgPath = new File(ImageName);
//		BufferedImage bufferedImage = ImageIO.read(imgPath);
//		
//		// get DataBufferBytes from Raster
//		WritableRaster raster = bufferedImage.getRaster();
//		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
//		
//		return (data.getData());
//	}
	
	// method to send an email to employee regarding reimbursement request status. 
	//Email sent by another thread
	private void email(String email, String message, String subject){
		
		class myRunnable implements Runnable {
			private String threadEmail = email;
			private String threadMessage = message;
			private String threadSubject = subject;
			@Override
			public void run() {
				// TODO Auto-generated method stub
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
			// set a default profile picture
			person.setProfilePic(ProfileImage.getGravatar80pxByte(person.getEmail()));
			System.out.println(person.getProfilePic());
			if(person.getProfilePic() == null) {
				person.setProfilePic(ProfileImage.getGravatar80pxByte
						("revature.reimbursements@gmail.com"));
			}
			// Save in Database
			dao.createUser(person);
			// Send Email to Account
			String subject = "Welcome to Revatuer";
			String message2 = 
			"<h1>Portfolio approved !!</h1>"
			+ "<table style=\"overflow-x:auto;max-width:100%;min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">"
            	+ "<tbody>"
            		+ "<tr>"
            			+ "<td style=\"padding-top:0;padding-right:18px;padding-bottom:9px;padding-left:18px;\" valign=\"top\">"
            				+ "<h1>Portfolio approved !!</h1>"
            				+ "<h3 style=\"text-align:left;\">Hello Alan Reilly</h3>"
            				+ "<p style=\"text-align:left;\"> Your portfolio Alan is approved on 11-Oct-2016."

            				+ "<br>"
            				+ "<br>"
            				+ "<br>Regards<br>"
            				+ "<strong>RevaturePro</strong><br>"
            				+ "<a rel=\"nofollow\" target=\"_blank\" href=\"http://track.revature.com/track/click/30470371/www.revature.com?p=eyJzIjoiQ29mc1ZvaXNQV2VhS0xkeE5CTEFBaTkyZ0JVIiwidiI6MSwicCI6IntcInVcIjozMDQ3MDM3MSxcInZcIjoxLFwidXJsXCI6XCJodHRwczpcXFwvXFxcL3d3dy5yZXZhdHVyZS5jb21cIixcImlkXCI6XCJmNjlmMGY3NWNhNzQ0ZTQyYmMzYzE0MjE5MWE2ZDEyYlwiLFwidXJsX2lkc1wiOltcImYzMzAwNzEwM2Y4MmM1NjdhOTcwODk3OGE1MjI3MjczN2I1YmFkOGNcIl19In0\">https://www.revature.com</a></p>"

            			+ "</td>"
            		+ "</tr>"
            	+ "</tbody>"
            + "</table>";
			String message = "Here is your loging information <br><br>" 
					+ "Username: " + person.getUsername() + "<br>" 
					+ "Password: " + password;
			email(person.getEmail(), message2, subject);
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
	
	

}
