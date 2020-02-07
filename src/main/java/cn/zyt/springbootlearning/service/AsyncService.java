package cn.zyt.springbootlearning.service;

public interface AsyncService {

    /**
     * 模拟报表生成时，调用的异步方法
     */
    void generateReportAsync();

    /**
     * 非异步方法
     */
    void generateReportSync();
}
