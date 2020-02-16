package com.gmail.supersonicleader.impl;

import java.util.List;

import com.gmail.supersonicleader.FileRepository;
import com.gmail.supersonicleader.NumberFileService;
import com.gmail.supersonicleader.NumberService;
import com.gmail.supersonicleader.exception.FileExistenceReadingException;
import com.gmail.supersonicleader.exception.NumberValidityException;
import org.springframework.stereotype.Service;

@Service
public class NumberFileServiceImpl implements NumberFileService {

    private final FileRepository fileRepository;
    private final NumberService numberService;

    public NumberFileServiceImpl(FileRepository fileRepository, NumberService numberService) {
        this.fileRepository = fileRepository;
        this.numberService = numberService;
    }

    @Override
    public int readForSum(String fileName) throws FileExistenceReadingException, NumberValidityException {
        List<String> lines = fileRepository.readByLines(fileName);
        int sum = 0;
        for (String line : lines) {
            int lineSum = numberService.add(line);
            sum += lineSum;
        }
        return sum;
    }

}
