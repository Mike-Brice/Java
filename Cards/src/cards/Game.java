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
 *
 * -----------------------------------------------------------------------------
 *
 * This program is intended to be an education tool
 *
 * -----------------------------------------------------------------------------
 *
 * This Class is for the JPanel for controlling all of the different card games
 */
package cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
public class Game extends JPanel {
    
    // =========================================================================
    // Fields
    // =========================================================================
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    // =========================================================================
    // Constructor
    // =========================================================================
    public Game() {
        setup();
    }
    
    // =========================================================================
    // Sets up the Panel
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Sets up the Panel
     */
    private void setup() {
        setLayout(null);
    }
    
    // =========================================================================
    // Overrides || Paint
    // =========================================================================
    
    @Override
    public void paint(Graphics g) {
        
        g.setColor(Color.black);
        g.fillRect(0,0, screenSize.width, screenSize.height);
        
        //Add Logo
        File logo = new File("Logo.png");
        Image image;
        try {
            image = ImageIO.read(logo);
            g.drawImage(image, 0, 20, this);
        } 
        catch (IOException ex) {
            //Do Nothing
        }
    }
}
