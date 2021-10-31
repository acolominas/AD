package client;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import ws.IOException_Exception;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

public class DisplayImage {

    public DisplayImage(String Path) 
    {
        try {
            BufferedImage img=ImageIO.read(new File(Path));
            ImageIcon icon=new ImageIcon(img);
            JFrame frame=new JFrame();
            frame.setLayout(new FlowLayout());
            frame.setSize(200,300);
            JLabel lbl=new JLabel();
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);            
        } catch (IOException ex) {
            // TODO handle custom exceptions here
                         
        }   
    }
}