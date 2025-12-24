package org.anas.chat_bot.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Arrays;

@Component
public class AiAgent {
    private ChatClient chatClient;

    public AiAgent(ChatClient.Builder builder, ChatMemory memory, ToolCallbackProvider tools) {

        Arrays.stream(tools.getToolCallbacks()).forEach(tool -> {
            System.out.println("=============================");
            System.out.println(tool.getToolDefinition());
            System.out.println("=============================");
        });

        this.chatClient = builder
                .defaultSystem("""
                        You are an assistant that answer user's questions based on the given context.
                        If the context is not available answer I DO NOT KNOW.
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor
                                .builder(memory)
                                .build()
                )
                .defaultToolCallbacks(tools)
                .build();
    }

    public String askAgent(String querry) {
        return chatClient.prompt()
                .user(querry)
                .call() .content();
    }
}
