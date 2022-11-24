package post.post.upc.edu.pe.posts.command.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OpenPostRequest {
    @Getter
    private BigDecimal postId;
    private @Getter BigDecimal overdraftLimit;
    @Getter @NotNull
    private String userId;
    @Getter @NotNull
    private String text;
}
