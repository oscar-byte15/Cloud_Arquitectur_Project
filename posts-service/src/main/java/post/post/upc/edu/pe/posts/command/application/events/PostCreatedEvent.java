package post.post.upc.edu.pe.posts.command.application.events;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class PostCreatedEvent {
    private String postId;
    private String userId;
    private BigDecimal overdraftLimit;
    private String text;
}
