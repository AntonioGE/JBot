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
package botaire.bot;

import botaire.bot.task.Delay;
import botaire.bot.task.Goto;
import botaire.bot.task.GotoAndClick;
import botaire.bot.task.SetDelay;
import botaire.bot.task.Task;
import botaire.bot.task.Type;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

/**
 *
 * @author ANTONIO
 */
public class TaskParser {

    private static HashMap<String, Class<? extends Task>> taskClasses = new HashMap<String, Class<? extends Task>>() {
        {
            put(Goto.tag, Goto.class);
            put(GotoAndClick.tag, GotoAndClick.class);
            put(Delay.tag, Delay.class);
            put(SetDelay.tag, SetDelay.class);
            put(Type.tag, Type.class);
        }
    };


    public static ArrayList<Task> parseDocument(String path) throws IOException {
        
        ArrayList<String> lines = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(path), Charset.defaultCharset())) {
            stream.forEachOrdered(line -> lines.add(line));
        }

        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            String[] sLine = line.split(" ");

            if (sLine.length > 0) {
                if (!sLine[0].startsWith("#")) {//Coments start with '#'
                    Class taskClass = taskClasses.get(sLine[0]);

                    if (taskClass != null) {
                        try {
                            Constructor taskConstr = taskClass.getConstructor(String[].class);

                            Object task = taskConstr.newInstance((Object) sLine);
                            tasks.add((Task) task);
                        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
                            System.out.println("Line not parsed: " + line);
                        }
                    } else {
                        System.out.println("Line not parsed: " + line);
                    }
                }
            }
        }
        return tasks;
    }

}
