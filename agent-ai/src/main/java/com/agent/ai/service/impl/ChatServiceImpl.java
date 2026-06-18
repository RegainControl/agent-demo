package com.agent.ai.service.impl;

import com.agent.ai.server.Tools;
import com.agent.ai.service.ChatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Autowired
    private OllamaChatModel chatModel;

    @Autowired
    private Tools tools;

    // 系统提示：告知模型可调用的工具及规则
    private static final String SYSTEM_PROMPT = """
            你是一个智能助手，当用户提出数学加法计算问题时，必须调用加法工具完成运算，不要自己口算。
                                         工具返回结果后直接整理输出给用户即可。
            """;

    @Override
    public String toolsChat(String message) {
        try {
            return ChatClient.create(chatModel)
                    .prompt()
                    .system(SYSTEM_PROMPT) // 系统提示：定义工具调用规则
                    .user(message) // 用户问题
                    .tools(tools) // 注册所有工具方法
                    .call()
                    .content();
        } catch (Exception e) {
            logger.error("工具调用失败", e);
            return "处理失败，请重试";
        }
    }


}