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

import com.sun.jna.platform.win32.WinDef;
import jbot.bot.TaskExecuter;
import jbot.utils.User32;

/**
 *
 * @author ANTONIO
 */
public class SelectWindow extends Task{

    public static final String tag = "SELECTWIN";
    public String winName;
    
    public SelectWindow(String winName){
        this.winName = winName;
    }
    
    public SelectWindow(String[] sCmd){
        this.winName = sCmd[1];
    }
    
    @Override
    public void execute(TaskExecuter exe) {
        selectWindow(winName);
    }
    
    private boolean selectWindow(String windowName) {
        User32 user32 = User32.instance;
        WinDef.HWND hWnd = user32.FindWindow(null, windowName); // Sets focus to my opened 'Downloads' folder
        if (hWnd != null) {
            user32.ShowWindow(hWnd, User32.SW_SHOW);
            user32.SetForegroundWindow(hWnd);
            return true;
        }
        return false;
    }
}
