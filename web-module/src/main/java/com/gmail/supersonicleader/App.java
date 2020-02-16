package com.gmail.supersonicleader;

import java.lang.invoke.MethodHandles;

import com.gmail.supersonicleader.exception.FileExistenceReadingException;
import com.gmail.supersonicleader.exception.NumberValidityException;
import com.gmail.supersonicleader.property.FileProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        FileProperty fileProperty = context.getBean(FileProperty.class);
        String numbersFilePath = fileProperty.getNumbersFilePath();

        NumberFileService numberFileService = context.getBean(NumberFileService.class);
        try {
            int sum = numberFileService.readForSum(numbersFilePath + "numbers.txt");
            logger.info("Sum of all numbers in the \"numbers\" file = " + sum);
        } catch (FileExistenceReadingException | NumberValidityException e) {
            logger.error(e.getMessage());
        }
    }

}
