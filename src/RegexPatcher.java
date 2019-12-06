import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPatcher {

    private List<String> text;

    RegexPatcher() throws FileNotFoundException {
        text = new ArrayList<>();
        enterText();
    }

    public void enterText() throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            text.add(sc.nextLine());
        }
    }

    public void removeBrackets() throws IOException {
        Pattern p = Pattern.compile("\\([^()]*\\)");
        Matcher m;
        try (FileWriter fw = new FileWriter("output.txt")) {
            for (String row : text) {
                String s = row.replaceAll(p.pattern(), "");
                m = p.matcher(s);
                while (m.find()) {
                    s = m.replaceAll("");
                    m = p.matcher(s);
                }
                s = s.replaceAll("[()]*", "");
                s = s.replaceAll("^\\s", "");
                fw.write(s + "\n");
            }
        }
    }

    public void removeNumbers() throws IOException {
        Pattern p = Pattern.compile("[0-9]{2}|[0-9]");
        Matcher m;
        StringTokenizer st;
        String temp;
        try (FileWriter fw = new FileWriter("output1.txt")) {
            for (String row : text) {
                st = new StringTokenizer(row, findDelims(row, '0', '9'), true);
                while (st.hasMoreTokens()) {
                    temp = st.nextToken();
                    m = p.matcher(temp);
                    if (m.find()) {
                        fw.write(m.group());
                    }
                    else {
                        fw.write(temp);
                    }
                }
                fw.write("\n");
            }
        }
    }

    public String findDelims(String word, char exceptFrom, char exceptTo) {
        StringBuilder delims = new StringBuilder();
        int rowLen = word.length();
        for (int i = 0; i < rowLen; i++) {
            if (!(word.charAt(i) >= exceptFrom && word.charAt(i) <= exceptTo)) {
                delims.append(word.charAt(i));
            }
        }
        return delims.toString();
    }

    public void removeZero() throws IOException {
        try (FileWriter fw = new FileWriter("output2.txt")) {
            Pattern p = Pattern.compile("^0+");
            Matcher m;
            StringTokenizer st;
            for (String row : text) {
                st = new StringTokenizer(row, findDelims(row, '0', '9'), true);
                while (st.hasMoreTokens()) {
                    m = p.matcher(st.nextToken());
                    if(m.matches()){
                        fw.write(m.replaceAll("0"));
                    }
                    else{
                        fw.write(m.replaceAll(""));
                    }
                }
                fw.write("\n");
            }
        }
    }
}
