package com.gmail.supersonicleader.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileProperty {

    @Value("${numbers.file.path:#{null}}")
    private String numbersFilePath;

    public String getNumbersFilePath() {
        return numbersFilePath;
    }

}
