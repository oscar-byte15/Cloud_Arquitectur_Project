package post.post.upc.edu.pe.posts.command.application.dto.response;

import lombok.Value;

import java.math.BigDecimal;
@Value
public class OpenPostResponse {
    private String postId;
    private String userId;
    private BigDecimal overdraftLimit;
    private String text;
}
