package project3.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import project3.dto.Person;
import project3.service.Crypt;
import project3.service.ServiceInterface;
import project3.simpledao.SimpleDao;

@Component
public class ServiceLogic implements ServiceInterface{
	
	private Crypt crypt;
	
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

	@Override
	public void createUser(Person person) {
		person.setUsername(person.getFirst_name() + "_" 
				+ person.getLast_name());
		// Check if email exists
		if(dao.getPersonByEmail(person.getEmail()) == null) {
			// Generate a Temporary Password
			String password = getRandom(20);
			person.setPassword(maskElement(password));
			// set a default profile picture
			
			// Send Email to Account
//			Mailer.sendMail(person.getEmail(), password);
			// Save in Database
			dao.createUser(person);
			//need to send an email
		} else {
			//tell that the email already exist
		}		
	}

}
