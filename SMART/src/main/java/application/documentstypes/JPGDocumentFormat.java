package application.documentstypes;

import application.models.user.IUserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@Slf4j
public class JPGDocumentFormat {
    @Autowired
    IUserModel userModel;

    private static Font getFont(String name, int size) {
        try {
            InputStream in = new FileInputStream(name);
            Font font = Font.createFont(Font.TRUETYPE_FONT, in);
            return font.deriveFont((float)size);
        } catch (Exception e) {
            log.info("getFont -> " + e.getMessage());
            return null;
        }
    }

    public void createInvitationFromTemplate(String template,
                                             String toFile,
                                             String fontname,
                                             Integer fontsize,
                                             Integer eventId,
                                             Integer userId) throws IOException {
        BufferedImage myPicture = ImageIO.read(new File(template));
        Graphics2D g = (Graphics2D) myPicture.getGraphics();
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLUE);
        //TODO
        ImageIO.write(myPicture, "jpg", new File(toFile));
        log.info("createPictureFromTemplate -> file of template is " + template + " > Save to file " + toFile + " > completed");
    }

}
