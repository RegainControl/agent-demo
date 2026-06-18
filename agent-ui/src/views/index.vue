<template>
  <div class="ai-chat-card">
    <div class="chat-header">
      <h3>AI智能问答 Demo</h3>
      <span>接口：GET /agent/ai/tools-chat</span>
    </div>

    <!-- 聊天消息容器 -->
    <div ref="chatScrollRef" class="chat-wrap">
      <div
        v-for="(item, idx) in messageList"
        :key="idx"
        :class="['msg-item', item.type === 'user' ? 'user-side' : 'ai-side']"
      >
        <div class="msg-bubble">{{ item.content }}</div>
      </div>
      <!-- AI加载提示 -->
      <div v-if="loading" class="msg-item ai-side">
        <div class="msg-bubble loading">AI正在思考...</div>
      </div>
    </div>

    <!-- 输入区域 -->
    <div class="input-wrap">
      <textarea
        v-model="question"
        placeholder="输入问题，回车发送"
        @keyup.enter="handleSend"
        class="question-input"
      ></textarea>
      <el-button type="primary" @click="handleSend" :loading="loading">发送</el-button>
    </div>
  </div>
</template>

<script>
import request from '@/utils/request'

export default {
  name: 'AiChatDemo',
  data() {
    return {
      question: '',
      loading: false,
      messageList: [
        {
          type: 'ai',
          content: '您好，我是AI问答助手，请输入你的问题，我会调用后端接口为你解答。'
        }
      ]
    }
  },
  methods: {
    // 发送消息（若依标准request GET请求）
    async handleSend() {
      const content = this.question.trim()
      if (!content || this.loading) return

      // 追加用户消息
      this.messageList.push({
        type: 'user',
        content
      })
      this.question = ''
      this.loading = true
      this.$nextTick(() => this.scrollToBottom())

      try {
        // 若依标准GET请求，params自动拼接URL参数 question
        const res = await request({
          url: '/agent/ai/tools-chat',
          method: 'get',
          params: {
            question: content
          }
        })

        let replyText = ''
        if (res.code === 200) {
          replyText = res.msg
        } else {
          replyText = `请求异常：${res.msg || '接口返回错误'}`
        }

        this.messageList.push({
          type: 'ai',
          content: replyText
        })
      } catch (error) {
        this.messageList.push({
          type: 'ai',
          content: '网络请求失败，请检查后端服务是否正常运行'
        })
      } finally {
        this.loading = false
        this.$nextTick(() => this.scrollToBottom())
      }
    },
    // 滚动聊天框到底部
    scrollToBottom() {
      const dom = this.$refs.chatScrollRef
      if (dom) {
        dom.scrollTop = dom.scrollHeight
      }
    }
  }
}
</script>

<style scoped>
.ai-chat-card {
  width: 100%;
  height: 620px;
  border: 1px solid #e4e7ed;
  border-radius: 8px;
  display: flex;
  flex-direction: column;
  background: #fff;
}
.chat-header {
  padding: 14px 20px;
  background: #409eff;
  color: #fff;
}
.chat-header h3 {
  margin: 0 0 4px;
  font-size: 16px;
}
.chat-header span {
  font-size: 12px;
  opacity: 0.85;
}
.chat-wrap {
  flex: 1;
  padding: 16px 20px;
  overflow-y: auto;
  background-color: #f5f7fa;
}
.msg-item {
  margin-bottom: 18px;
  display: flex;
}
.user-side {
  justify-content: flex-end;
}
.ai-side {
  justify-content: flex-start;
}
.msg-bubble {
  max-width: 72%;
  padding: 9px 15px;
  border-radius: 18px;
  line-height: 1.6;
  word-break: break-all;
}
.user-side .msg-bubble {
  background: #409eff;
  color: #fff;
  border-bottom-right-radius: 4px;
}
.ai-side .msg-bubble {
  background: #ffffff;
  border: 1px solid #e4e7ed;
  color: #303133;
  border-bottom-left-radius: 4px;
}
.loading {
  color: #909399;
}
.input-wrap {
  display: flex;
  gap: 12px;
  padding: 14px 20px;
  border-top: 1px solid #e4e7ed;
  align-items: flex-end;
}
.question-input {
  flex: 1;
  min-height: 50px;
  resize: none;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  padding: 10px 14px;
  font-size: 14px;
}
.question-input:focus {
  outline: none;
  border-color: #409eff;
}
</style>