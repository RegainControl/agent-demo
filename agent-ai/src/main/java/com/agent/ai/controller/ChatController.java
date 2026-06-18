package com.agent.ai.controller;

import com.agent.ai.service.ChatService;
import com.agent.common.core.domain.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * AI智能助手控制器
 * 处理AI问答
 *
 * @author zyj
 * @date 2026-06-17
 */
@RestController
@RequestMapping("/agent/ai")
public class ChatController {

    /**
     * 日志记录器
     */
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    /**
     * 智能问答服务
     */
    @Autowired
    private ChatService chatService;

    @GetMapping("/tools-chat")
    public AjaxResult toolsChat(@RequestParam String question) {
        logger.info("接收问题: {}", question);
        long start = System.currentTimeMillis();

        String result = chatService.toolsChat(question);
        logger.info("模型返回:" + result);

        long timeElapsed = System.currentTimeMillis() - start;
        logger.info("处理耗时: {}ms", timeElapsed);
        return AjaxResult.success(result);
    }

}