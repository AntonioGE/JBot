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
package jbot.utils;

import java.awt.Point;
import java.awt.image.BufferedImage;

/**
 *
 * @author ANTONIO
 */
public class Utils {
    public static Point findImage(BufferedImage imgBig, BufferedImage img, float tolerance) {
        for (int i = 0; i < imgBig.getWidth() - img.getWidth(); i++) {
            for (int j = 0; j < imgBig.getHeight() - img.getHeight(); j++) {
                float norm = imageDifferenceNorm(imgBig.getSubimage(i, j, img.getWidth(), img.getHeight()), img);
                if (norm < tolerance) {
                    return new Point(i, j);
                }
            }
        }

        return null;
    }

    public static float imageDifferenceNorm(BufferedImage img1, BufferedImage img2) {
        float totalDiff = 0.0f;
        try {
            for (int i = 0; i < img1.getWidth(); i++) {
                for (int j = 0; j < img1.getHeight(); j++) {
                    final int c1 = img1.getRGB(i, j);
                    final int c2 = img2.getRGB(i, j);

                    totalDiff += Math.abs((((c1 & 0x00FF0000) >> 16) - ((c2 & 0x00FF0000) >> 16)));
                    totalDiff += Math.abs((((c1 & 0x0000FF00) >> 8) - ((c2 & 0x0000FF00) >> 8)));
                    totalDiff += Math.abs(((c1 & 0x000000FF) - (c2 & 0x000000FF)));
                }
            }
            return totalDiff / (img1.getWidth() * img1.getHeight() * 255 * 3);
        } catch (Exception ex) {
            return 1.0f;
        }
    }
}
