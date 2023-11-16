package com.qf.pay.service;

import com.qf.core.vo.R;

public interface IPayService {
    R pay(String orderId);
    String isPay(String resultXML) throws Exception;
}
