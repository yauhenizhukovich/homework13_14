package com.gmail.supersonicleader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.gmail.supersonicleader.FileRepository;
import com.gmail.supersonicleader.exception.FileExistenceReadingException;
import org.springframework.stereotype.Repository;

@Repository
public class FileRepositoryImpl implements FileRepository {

    @Override
    public List<String> readByLines(String fileName) throws FileExistenceReadingException {
        File file = new File(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            List<String> fileLines = readAndCollectLines(br);
            return fileLines;
        } catch (IOException e) {
            throw new FileExistenceReadingException("File does not exist or cannot be read!");
        }
    }

    private List<String> readAndCollectLines(BufferedReader br) throws IOException {
        List<String> fileLines = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        int symbol = br.read();
        while (symbol != -1) {
            if ((char) symbol == '\r') {
                symbol = addLineAndGetFirstSymbolFromNextLine(br, fileLines, line);
                if ((char) symbol == '\r') {
                    continue;
                }
            }
            if (symbol != -1) {
                line.append((char) symbol);
                symbol = br.read();
            }
        }
        fileLines.add(line.toString());
        return fileLines;
    }

    private int addLineAndGetFirstSymbolFromNextLine(BufferedReader br, List<String> fileLines, StringBuilder line) throws IOException {
        int symbol;
        br.read();
        symbol = br.read();
        fileLines.add(line.toString());
        line.delete(0, line.length());
        return symbol;
    }

}
