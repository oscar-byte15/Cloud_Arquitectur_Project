package post.post.upc.edu.pe.posts.command.application.commands;

import lombok.Getter;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Value
public class CreatePost {
    @TargetAggregateIdentifier
    private String postId;
    private String userId;
    private BigDecimal overdraftLimit;
    private String text;
}
