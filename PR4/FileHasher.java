package PR4;
import java.io.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;

public class FileHasher {
    private ExecutorService executor;

    public FileHasher(int threadCount) {
        this.executor = Executors.newFixedThreadPool(threadCount);
    }

    public Map<String, byte[]> hashFiles(List<File> files) throws InterruptedException, ExecutionException {
        Map<String, Future<byte[]>> futures = new HashMap<>();
        for (File file : files) {
            // Отправляет каждый файл на выполнение в отдельном потоке
            Future<byte[]> future = executor.submit(new HashTask(file));
            futures.put(file.getName(), future);
        }

        Map<String, byte[]> hashes = new HashMap<>();
        for (String filename : futures.keySet()) {
            Future<byte[]> future = futures.get(filename);
            // future.get() ожидает завершения всех потоков
            hashes.put(filename, future.get());
        }

        return hashes;
    }

    private static class HashTask implements Callable<byte[]> {
        private File file;

        public HashTask(File file) {
            this.file = file;
        }

        @Override
        public byte[] call() throws Exception {
            // Каждый поток вычисляет хэш-сумму файла с помощью MessageDigest.
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream input = new BufferedInputStream(new FileInputStream(file))) {
                byte[] buffer = new byte[8192];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    digest.update(buffer, 0, bytesRead);
                }
            }
            return digest.digest();
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        List<File> files = Arrays.asList(
                new File("C:\\Users\\BadHard\\IdeaProjects\\Java_Framework_Templates\\src\\PR4\\file1.txt"),
                new File("C:\\Users\\BadHard\\IdeaProjects\\Java_Framework_Templates\\src\\PR4\\file2.txt"),
                new File("C:\\Users\\BadHard\\IdeaProjects\\Java_Framework_Templates\\src\\PR4\\file3.txt")
        );

        // FileHasher создает пул из трех потоков (newFixedThreadPool(3))
        FileHasher hasher = new FileHasher(3);
        Map<String, byte[]> hashes = hasher.hashFiles(files);

        for (String filename : hashes.keySet()) {
            byte[] hash = hashes.get(filename);
            System.out.println(filename + ": " + Base64.getEncoder().encodeToString(hash));
        }

        hasher.executor.shutdown();
    }
}
