package com.gmail.supersonicleader;

import com.gmail.supersonicleader.exception.NumberValidityException;

public interface NumberService {

    int add(String numbers) throws NumberValidityException;

}
