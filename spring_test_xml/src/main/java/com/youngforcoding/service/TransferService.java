package com.youngforcoding.service;

/**
 * @author 应癫
 */
public interface TransferService {

    void transfer(String fromCardNo, String toCardNo, int money) throws Exception;

    void transfer() throws Exception;
}
