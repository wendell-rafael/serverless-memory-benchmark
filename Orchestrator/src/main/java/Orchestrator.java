import benchmark.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class Orchestrator {
    public static void sleep(String input, int n) throws Exception {
        Benchmark b = new Sleep();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long size = (Long) jsonObject.get("size");
        String[] args = new String[]{"-s", String.valueOf(size)};

        b.execute(n, file, args);

        file.close();
    }

    public static void dynamicHTML(String input, int n) throws Exception {
        Benchmark b = new DynamicHTML();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long length = (Long) jsonObject.get("length");
        String user = (String) jsonObject.get("user");
        String[] args = new String[]{"-user", user, "-len", String.valueOf(length)};

        b.execute(n, file, args);

        file.close();
    }

    public static void graphBFS(String input, int n) throws Exception {
        Benchmark b = new GraphBFS();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long size = (Long) jsonObject.get("size");
        String[] args = new String[]{"-size", String.valueOf(size)};

        b.execute(n, file, args);

        file.close();
    }

    public static void thumbnailer(String input, int n) throws Exception {
        Benchmark b = new Thumbnailer();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long width = (Long) jsonObject.get("width");
        Long height = (Long) jsonObject.get("height");
        String filePath = (String) jsonObject.get("filePath");
        String destPath = (String) jsonObject.get("destPath");
        String[] args = new String[]{"-f", filePath, "-d", destPath, "-w", String.valueOf(width), "-h", String.valueOf(height)};

        b.execute(n, file, args);

        file.close();
    }

    public static void videoProcessing(String input, int n) throws Exception {
        Benchmark b = new VideoProcessing();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long duration = (Long) jsonObject.get("duration");
        String videoPath = (String) jsonObject.get("videoPath");
        String operation = (String) jsonObject.get("operation");
        String[] args = new String[]{"-v", videoPath, "-d", String.valueOf(duration), "-o", operation};

        b.execute(n, file, args);

        file.close();
    }

    public static void fibonacci(String input, int n) throws Exception {
        Benchmark b = new Fibonacci();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long ithNumber = (Long) jsonObject.get("n");
        String[] args = new String[]{"-n", String.valueOf(ithNumber)};

        b.execute(n, file, args);

        file.close();
    }

    public static void factorial(String input, int n) throws Exception {
        Benchmark b = new Factorial();

        FileWriter file = createOutputFile(b.getName());
        JSONObject jsonObject = readInput(input);

        Long number = (Long) jsonObject.get("n");
        String[] args = new String[]{"-n", String.valueOf(number)};

        b.execute(n, file, args);

        file.close();
    }

    private static JSONObject readInput(String input) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader(input));
        JSONObject jsonObject = (JSONObject) obj;
        return jsonObject;
    }

    private static FileWriter createOutputFile(String benchmark) throws IOException {
        FileWriter file = new FileWriter("results/" + benchmark + ".csv");
        file.write("benchmark,req_id,init_time,end_time,duration\n");
        return file;
    }

}
