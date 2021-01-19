/*
 * MIT License
 * 
 * Copyright (c) 2021 Antonio GE
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jbot.bot.task;

import jbot.bot.Bot;
import jbot.utils.Utils;
import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import jbot.bot.TaskExecuter;

/**
 *
 * @author ANTONIO
 */
public class FindWindow extends Task {

    public String imageName;
    public int xOffset, yOffset;
    
    public FindWindow(String[] sCmd){
        this.imageName = sCmd[1];
        this.xOffset = Integer.parseInt(sCmd[2]);
        this.yOffset = Integer.parseInt(sCmd[3]);
    }
    
    @Override
    public void execute(TaskExecuter exe) {
        try {
            BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + "/images/" + imageName));
            BufferedImage screenImg = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            
            Utils.findImage(screenImg, img, 0.05f);
        } catch (IOException | AWTException ex) {
        
        }
    }
    
}
