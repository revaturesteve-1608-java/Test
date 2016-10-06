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
import project3.simpledao.SimpleDao;
import project3.util.Email;

@Component
public class ServiceLogic implements ServiceInterface{
	
	@Autowired
	Crypt crypt;
	
	/**
	 * Using to access the database
	 */
	@Autowired // mapped to the bean
	SimpleDao dao;
	
	private String getRandom(int length){
		return crypt.getRandom(length);
	}
	
	private String maskElement(String target){			
		return crypt.encrypt(target);
	}
	
	private byte[] extractBytes (String ImageName) throws IOException {
		// open image
		File imgPath = new File(ImageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);
		
		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage .getRaster();
		DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();
		
		return ( data.getData() );
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
				// TODO Auto-generated method stub
				Email.sendEmail(threadEmail, threadMessage, threadSubject);
			}
		}
		Thread t = new Thread(new myRunnable());
		t.start();
	}

	@Override
	public void createUser(Person person) {
		// Check if email exists
		if(dao.getPersonByEmail(person.getEmail()) == null) {
			//make new user name 
			person.setUsername(person.getEmail());
			System.out.println("password: " + person.getPassword());
			// Generate a Temporary Password
			String password = getRandom(20);
			System.out.println("password2: " + password);
			person.setPassword(maskElement(password));
			System.out.println("password3: " + person.getPassword());
			// set a default profile picture
			
			// Save in Database
			dao.createUser(person);
			// Send Email to Account
			String subject = "Welcome to Revatuer";
			String message = "Here is your new password: " + password;
			email(person.getEmail(), message, subject);
		} else {
			//tell that the email already exist
		}		
	}

	@Override
	public List<Role> getRoles() {
		return dao.getRoles();
	}
	
	

}
