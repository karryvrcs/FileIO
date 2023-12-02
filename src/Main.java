import java.io.*;


public class Main {
    public static void main(String[] args) {
        File file = new File("fileOut.txt");
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        String pathIn = "file.txt";
        String pathOut = "fileOut.txt";

        //字节流
        try (FileInputStream fileInputStream = new FileInputStream(pathIn);
             FileOutputStream fileOutputStream = new FileOutputStream(pathOut)){
            int data; // ASCII   int representing byte
            byte[] block = new byte[10] ;
            while ((data = fileInputStream.read(block)) != -1){
                String content = new String(block);
                System.out.println(data + "  " + content);
                fileOutputStream.write(block); // 写入byte[]
            }
        } catch (IOException e){
            e.printStackTrace();
        }


        System.out.println("----------------------------");

        //字符流
        try (FileReader reader = new FileReader("file.txt")){
            char[] block = new char[10];//每次期望读取的最大字符数
            int data;
            while ((data = reader.read(block)) != -1) { //reader.read()会返回每次读取的字符数，当没有读取到字符时，返回-1
                String content = new String(block, 0, data); //从0处开始提取data个字符
                System.out.printf("---> [%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("----------------------------");

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("file.txt"))){
            String line;
            bufferedReader.mark(200);
            //This method take an integer argument, which specifies the limit of the number of characters can be read
            // after the mark position. trying to read more characters beyond this limit will make it impossible to return to the mark.
            while((line = bufferedReader.readLine()) != null) { // return a String
                System.out.println(line);
            }
            bufferedReader.reset();
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}