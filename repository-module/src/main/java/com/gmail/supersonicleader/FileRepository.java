package com.gmail.supersonicleader;

import java.util.List;

import com.gmail.supersonicleader.exception.FileExistenceReadingException;

public interface FileRepository {

    List<String> readByLines(String fileName) throws FileExistenceReadingException;

}
