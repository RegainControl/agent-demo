<p align="center">
	<img alt="logo" src="https://oscimg.oschina.net/oscnet/up-d3d0a9303e11d522a06cd263f3079027715.png">
</p>

<h1 align="center" style="margin: 30px 0 20px; font-weight: bold;">Agent v3.9.2</h1>

<h4 align="center">基于 Spring Boot + Spring AI + Vue 的 AI 智能助手平台</h4>

<p align="center">
	<a href="https://gitee.com/y_project/RuoYi-Vue">
		<img src="https://img.shields.io/badge/RuoYi-v3.9.2-brightgreen.svg">
	</a>
	<a href="https://spring.io/projects/spring-boot">
		<img src="https://img.shields.io/badge/SpringBoot-3.3.x-blue.svg">
	</a>
	<a href="https://ollama.ai">
		<img src="https://img.shields.io/badge/Ollama-Local%20LLM-orange.svg">
	</a>
	<a href="https://opensource.org/licenses/MIT">
		<img src="https://img.shields.io/badge/license-MIT-green.svg">
	</a>
</p>

---

## 🚀 项目简介

Agent 是一个基于 **Spring Boot + Spring AI + Ollama** 构建的本地 AI 智能助手系统，支持：

- 💬 大模型对话（Ollama / Qwen / Llama）
- 🧠 Tool Calling（工具调用）
- 🌐 前后端分离（Vue）

适用于：
- AI Agent 项目实践
- 企业知识库问答系统
- 本地私有化大模型应用

---

## 🧱 技术栈

### 后端
- Spring Boot 3.3.x
- Spring AI 1.0.x
- Ollama
- JWT + Security
- MyBatis

### AI能力
- Chat Model（Qwen3 / Llama3）
- Embedding Model（nomic-embed-text）
- Tool Calling（函数调用）

---

## 🧠 已实现能力

### ✔ 基础AI对话
- 接入 Ollama 本地大模型
- 支持流式/非流式对话

### ✔ 工具调用（Tool Calling）
- 自动调用 Java 方法
- 示例：加法计算工具

### ✔ RAG（规划中 / 可扩展）
- 向量数据库检索
- 文档 embedding
- 知识库问答

---

## 🗄 系统模块结构
