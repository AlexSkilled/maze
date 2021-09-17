package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Counter {

    String[] paths;
    Field[] fields;

    public Counter() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("file.txt"));

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
        }
        String everything = sb.toString();
        System.out.println(everything);

        br.close();
        paths = everything.split("\r\n");
        int amount = parseInt(paths[0]);

        fields = new Field[amount];
        String[] newArray = new String[amount];
        System.arraycopy(paths, 1, newArray, 0, paths.length-1);
        paths = newArray;
    }

    public void start() {
        for (int i = 0; i < paths.length; i++) {
            fields[i] = new Field(paths[i]);
            System.out.println("Case #" + (i+1) +"\n" + fields[i].getResult());
        }
    }
}
