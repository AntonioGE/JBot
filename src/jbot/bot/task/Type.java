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
import jbot.bot.TaskExecuter;

/**
 *
 * @author ANTONIO
 */
public class Type extends Task{

    public static final String tag = "TYPE";
    public String text;
    
    public Type(String text){
        this.text = text;
    }
    
    public Type(String[] sCmd){
        text = "";
        int i;
        for( i = 1; i < sCmd.length - 1; i++){
            text += sCmd[i] + " ";
        }
        text += sCmd[i];
    }
    
    @Override
    public int execute(TaskExecuter exe) {
        exe.bot.combinePress(17, 65);
        exe.bot.sleep();
        exe.bot.manuPress(8);
        exe.bot.sleep();

        exe.bot.toClippboard(text);
        exe.bot.sleep();
        exe.bot.combinePress(17, 86);
        exe.bot.sleep();
        
        return Task.RESULT_OK;
    }
    
    
    
}
