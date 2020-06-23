/*
 * Copyright (C) 2017 Couchoutput Studios
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/*
 * This program is intended to be an education tool
 */
package simon;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class Screen extends JPanel {
    
    //Fields
    private boolean blue, red, green, yellow, over;
    
    public Screen() {
         
        blue = false;
        red = false;
        green = false;
        yellow = false;
        over = false;
        
        setup();
    }
    
    private void setup() {
        setLayout(null);
    }
    
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }
    
    public void updateColors(boolean blue, boolean red, boolean green, boolean yellow) {
        this.blue = blue;
        this.red = red;
        this.green = green;
        this.yellow = yellow;
    }
    
    public void setGameOver() {
        this.over = true;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        //Sets the background
        g.setColor(Color.black);
        g.fillRect(0,0, getWidth(), getHeight());
        
        //Sets up background image
        //Add Background
        
        File background = new File("background3.png");
        Image img;
        try {
            img = ImageIO.read(background);

            g.drawImage(img, 0, 0, this);
        } 
        catch (IOException ex) {
            //Do Nothing
        }
        
        
        //Sets up the simon wheel

        //Outer White Circle / Ring
        g.setColor(Color.white);
        g.fillOval(550, 80, 840, 840);
        
        //Outer Black Circle / Ring
        g.setColor(Color.black);
        g.fillOval(555, 85, 830, 830);
        
        //Red
        g.setColor(new Color(128,0,0));
        g.fillArc(575, 95, 800, 800, 0, 90);
        
        //Green
        g.setColor(new Color(0,128,0));
        g.fillArc(565, 95, 800, 800, 90, 90);
            
        //Yellow
        g.setColor(new Color(179, 170, 0));
        g.fillArc(565, 105, 800, 800, 180, 90);
        
        //Blue
        g.setColor(new Color(0,128,192));
        g.fillArc(575,105, 800, 800, 270, 90);
       
        
        
        if (over) {
        }
        
        else {

            if (blue) {
                //Blue
                g.setColor(new Color(0,255,255));
                g.fillArc(575,105, 800, 800, 270, 90);
            }

            if (red) {
                //Red
                g.setColor(new Color(255,0,0));
                g.fillArc(575, 95, 800, 800, 0, 90);
            }

            if (green) {
                //Green
                g.setColor(new Color(0,255,0));
                g.fillArc(565, 95, 800, 800, 90, 90);
            }

            if (yellow) {
                //Yellow
                g.setColor(new Color(255, 255, 0));
                g.fillArc(565, 105, 800, 800, 180, 90);
            }
            
            
            //Inner Circles
            //First Black
            g.setColor(Color.black);
            g.fillOval(755, 285, 430, 430);
            
            //Gray Ring
            g.setColor(Color.white);
            g.fillOval(765, 295, 410, 410);
            
            //Inner Black
            g.setColor(Color.black);
            g.fillOval(770, 300, 400, 400);
            
            //Texture Rings
            
            //Black
            g.setColor(new Color(0,0,0,50).darker());
            
            int x = 570;
            int y = 100;
            int radius = 800;
            
            for (int i = 0; i < 19; i++) {
                g.drawOval(x, y, radius, radius);
                x += 10;
                y += 10;
                radius -= 20;
            }
            
            x += 20;
            y += 20;
            radius -= 40;
            
            //White
            g.setColor(new Color(255,255,255,50).darker());
            for (int i = 0; i < 15; i++) {
                g.drawOval(x, y, radius, radius);
                x += 10;
                y += 10;
                radius -= 20;
            }
            
            //Add Logo
            File logo = new File("logo3.png");
            Image image;
            try {
                image = ImageIO.read(logo);
                g.drawImage(image, 790, 400, this);
            } 
            catch (IOException ex) {
                //Do Nothing
            }
        }
    }
}
