package benchmark;

import java.io.FileWriter;
import java.nio.file.NotDirectoryException;
import java.io.File;
import java.lang.Runtime;

public class VideoProcessing implements Benchmark {

    private static final String name = "video-processing";

    public String getName() {
        return name;
    }

    public void execute(int n, int batch_size, FileWriter file, String[] args) throws Exception {
        Runtime rt = Runtime.getRuntime();

        File dir = new File(args[1]);

        if (!dir.isDirectory()) {
            throw new NotDirectoryException("Given path is not a directory: " + dir.getAbsolutePath());
        }

        String files[] = dir.list();
        int nfiles = files.length;

        for (int j = 0; j < n; j++) {
            System.gc();

            for (int i = 0; i < batch_size; i++) {
                System.out.println("Execution " + i);
                args[1] = files[i % nfiles];
                long free_before = rt.freeMemory();
                long start = System.currentTimeMillis();

                videoprocessing.Main.main(args);

                long free_after = rt.freeMemory();
                long allocated = (free_before - free_after);
                long end = System.currentTimeMillis();
                long duration = end - start;

                file.write(String.format("%s,%s,%s,%s,%s,%s\n", this.getName(), i, start, end, duration, allocated));
            }
        }
    }

}
