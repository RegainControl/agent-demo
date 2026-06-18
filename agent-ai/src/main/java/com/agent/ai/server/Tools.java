package com.agent.ai.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
public class Tools {

    /**
     * 简单测试工具：两数求和
     * 当用户提问包含“相加、求和、计算a加b”时，大模型会自动调用该工具
     * @param num1 第一个数字
     * @param num2 第二个数字
     * @return 两数相加结果
     */
    @Tool(description = "用于计算两个数字相加求和，用户需要加法运算时调用这个工具")
    public String addTest(Double num1, Double num2) {
        double sum = num1 + num2;
        return "【工具计算结果】" + num1 + " + " + num2 + " = " + sum;
    }

}