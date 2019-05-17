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
 * This program is intended to be an education tool.
 *
 * -----------------------------------------------------------------------------
 *
 * This Class is for the Panel that is used by the Graphical User Interface for 
 * BlackJack of the CardsGUI class. It is supposed to run in parallel with the 
 * BlackJack and BlackJackData Classes.
 */
package cards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Mike Brice - Couchoutput Studios
 */
class BlackJackPanel extends JPanel {
    
    // =========================================================================
    // Fields
    // =========================================================================
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int player;
    private BlackJackData[] playerData;
    private final JButton button;
    
    // =========================================================================
    // Constructor
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * Default Constructor
     * @param playerData
     */
    public BlackJackPanel() {
        this.button = new JButton("Button");
        this.player = -1;
        //setup();
    }
    
    public void setup() {
        setLayout(null);
        add(button);
    }
    // =========================================================================
    // Setters
    // =========================================================================
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param playerData 
     */
    public void setBlackJackData(BlackJackData[] playerData) {
        this.playerData = playerData;
    }
    
    // -------------------------------------------------------------------------
    /**
     * 
     * @param player 
     */
    public void hitMe(int player) {
        this.player = player;
    }
    
    // =========================================================================
    // Overrides || Paint
    // =========================================================================
    
    // -------------------------------------------------------------------------
    @Override
    public void paint(Graphics g) {
        
        //Initial Screen
        g.setColor(Color.gray);
        g.fillRect(0,0,screenSize.width, screenSize.height);
        
        g.setColor(Color.black);
        g.fillOval(-10, -8, screenSize.width / 2 + screenSize.width / 3 + screenSize.width / 6 + screenSize.width / 80, screenSize.height/2 + screenSize.height / 3 + screenSize.height / 8);
        
        //Add Images
        File back = new File("BlackBack.png");
        File blackJack = new File("LogoBJ.png");
        File table = new File("Table.png");
        Image image;
        try {
            
            //Table
            image = ImageIO.read(table);
            g.drawImage(image, 0, 0, this);
            
            //Logo
            image = ImageIO.read(blackJack);
            g.drawImage(image, 1350, 20, this);
            
            //Deck of Cards
            image = ImageIO.read(back);
            
            //Initial coords of the deck
            int x1 = 900;
            int y1 = 400;
            int x2 = 1050;
            int y2 = 600;
            for (int i = 0; i < 10; i++) {
                g.drawImage(image, x1, y1, x2, y2, 0, 0, 368, 546, this);
                x1++;
                x2++;
                y1--;
                y2--;
            } 
            
            //User cards and buttons and information
            g.drawImage(playerData[0].peekCard(0).getGraphic(), 850,700,1000,900,0,0,368,546, this); // x1,y1,x2,y2
            g.drawImage(playerData[0].peekCard(1).getGraphic(), 900,710,1050,910,0,0,368,546,this);
            
            /*g.setColor(Color.white);
            Font font = new Font("Button Text", Font.PLAIN, 30);
            g.setFont(font);
            g.fillRect(1095, 775, 95 , 30);
            g.setColor(Color.BLACK);
            g.drawString("Hit Me", 1100, 800);*/
            
            //Bot 1 cards
            g.drawImage(rotate90((BufferedImage)image), 140,400,365,550,0,0,546,368,this);
            g.drawImage(rotate90((BufferedImage)playerData[1].peekCard(1).getGraphic()), 150,440,375,590,0,0,546,368,this); //150,410,300,610
            
            //Bot 2 cards
            g.drawImage(rotate180((BufferedImage)image), 850,100,1000,300,0,0,368,546,this);
            g.drawImage(rotate180((BufferedImage)playerData[2].peekCard(1).getGraphic()), 900,90,1050,290,0,0,368,546,this);
            
            //Bot 3 cards
            //g.drawImage(image, 850,100,1000,300,0,0,368,546,this);
        }
        
        catch (IOException e) {
            //Do Nothing
        }
        
        //During the game screen
        //Hit Me cards
        switch (player) {
            default: {
                //Do Nothing
                break;
            }
            case 0: { //Player 1
                break;
            }
            case 1: { //Player 2
                break;
            }
            case 2: { //Player 3
                break;
            }
            case 3: { //Player 4
                break;
            }
        }
    }
    
    private Image rotate90(BufferedImage img) {
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImage = new BufferedImage(height, width, img.getType());

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                newImage.setRGB(height - 1 - j, i, img.getRGB(i, j));
            }
        }
        return newImage;
    }
    
    private Image rotate180(BufferedImage img) {
        Image newImage = rotate90(img);
        return rotate90((BufferedImage)newImage);
    }
    
    private Image rotate270(BufferedImage img) {
        Image newImage = rotate90(img);
        newImage = rotate90((BufferedImage)newImage);
        return rotate90((BufferedImage)newImage);
    }
}
