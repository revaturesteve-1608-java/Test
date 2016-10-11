package project3.util;

import com.timgroup.jgravatar.Gravatar;
import com.timgroup.jgravatar.GravatarRating;
import com.timgroup.jgravatar.GravatarDefaultImage;

public class ProfileImage {

	public static String getGravatar160pxUrl(String email) {
	    Gravatar gravatar = new Gravatar();
	    gravatar.setSize(160);
	    gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
	    gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
	    return gravatar.getUrl(email);
	}

	public static String getGravatar80pxUrl(String email) {
	    Gravatar gravatar = new Gravatar();
	    gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
	    gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
	    return gravatar.getUrl(email);
	}
	
	public static byte[] getGravatar80pxByte(String email) {
		Gravatar gravatar = new Gravatar();
	    gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
	    gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
	    return gravatar.download(email);
	}

}
