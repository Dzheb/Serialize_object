package org.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class Homework {
    /**
     * Написать класс с двумя методами:
     * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
     * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
     *
     * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
     */
    public static void main(String[] args) {

        Path pathDirCreated = createDir("root");
//        write to file serialized object createFileClass
//
        Department department = new Department("Sales");
//        Path fileNameCreated = createFileClass(department,
//                String.valueOf(pathDirCreated));
//        Person person = new Person("Mike Tyson", 59);
        Path fileNameCreated = null;
        try {
            fileNameCreated = createFileClass(department,
                    String.valueOf(pathDirCreated));
        } catch (IOException e) {
            System.out.println("Fail to create fileClass serialized");
            throw new RuntimeException(e);
        }
// read fileClass deserialize and delete file
        try {
            System.out.println(readFileClass(fileNameCreated.toString()));
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
// create directory
    public static Path createDir(String dir) {
        //        creating directory root
        Path dirRoot = Path.of(System.
                getProperty("user.dir") + "/" + dir);
        if (!Files.exists(dirRoot)) {
            try {
                Files.createDirectories(dirRoot);
                System.out.println(dirRoot + " created");
            } catch (IOException e) {
                throw new RuntimeException(
                        "Error in creating directory" + dirRoot +
                                e);
            }
        }
        return dirRoot;
    }

    // 1. creating serialized fileClass
    public static <T> Path createFileClass(T fileClassEx, String dirRoot) throws IOException {
        // creating file in root dir
        String pathFile = fileClassEx.getClass().getName() + "_" + UUID.randomUUID();
        Path pathfileClass = Path.of(dirRoot + "\\/" + pathFile);
        if (!Files.exists(pathfileClass)) {
            try {
                Files.createFile(pathfileClass);

            } catch (IOException e) {
                throw new RuntimeException("Error creating file"
                        + e);
            }
        }
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream(String.valueOf(pathfileClass));
            ObjectOutputStream objectOutputStream =
                    new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(fileClassEx);
            fileOutputStream.close();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException("Error in writing to file"
                    + e);
        }
        return pathfileClass;
    }

    // 2.    reading serialized class from file and deleting fileClass
    public static Object readFileClass(String file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream =
                new FileInputStream(file);
        ObjectInputStream objectInputStream =
                new ObjectInputStream(fileInputStream);
        Object res = objectInputStream.readObject();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        fileInputStream.close();
        try {
            Files.delete(Path.of(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return res;
    }

}

