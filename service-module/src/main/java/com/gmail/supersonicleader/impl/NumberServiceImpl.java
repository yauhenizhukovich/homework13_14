package com.gmail.supersonicleader.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gmail.supersonicleader.NumberService;
import com.gmail.supersonicleader.exception.NumberValidityException;
import org.springframework.stereotype.Service;

import static com.gmail.supersonicleader.constant.NumberConstant.*;

@Service
public class NumberServiceImpl implements NumberService {

    @Override
    public int add(String numbers) throws NumberValidityException {
        if (numbers.equals("")) {
            return 0;
        }
        checkIfArgumentsCountValid(numbers);
        String[] localNumbers = numbers.split(NUMBER_SEPARATION_REGEXP);
        Pattern pattern = Pattern.compile(UNRESOLVED_CHARACTERS_IN_NUMBERS_REGEXP);
        int sum = 0;
        for (String numberString : localNumbers) {
            checkIfEmptyArgument(numberString);
            Matcher matcher = pattern.matcher(numberString);
            if (matcher.find()) {
                throw new NumberValidityException("Strings should contain only digits!");
            } else {
                int number = Integer.parseInt(numberString);
                sum += number;
            }
        }
        return sum;
    }

    private void checkIfEmptyArgument(String numberString) throws NumberValidityException {
        if (numberString.equals("")) {
            throw new NumberValidityException("Empty argument can only be confirmed if the entire string is empty!");
        }
    }

    private void checkIfArgumentsCountValid(String numbers) throws NumberValidityException {
        Pattern separatorPattern = Pattern.compile(NUMBER_SEPARATION_REGEXP);
        Matcher separatorMatcher = separatorPattern.matcher(numbers);
        int countOfSeparators = 0;
        while (separatorMatcher.find()) {
            countOfSeparators++;
        }
        if (countOfSeparators > MAX_SEPARATORS_IN_LINE) {
            throw new NumberValidityException("Too much arguments in one of the lines!");
        }
    }

}
