package com.gmail.supersonicleader;

import com.gmail.supersonicleader.exception.FileExistenceReadingException;
import com.gmail.supersonicleader.exception.NumberValidityException;

public interface NumberFileService {

    int readForSum(String fileName) throws FileExistenceReadingException, NumberValidityException;

}
