package post.post.upc.edu.pe.posts.command.domain;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import post.post.upc.edu.pe.posts.command.application.commands.CreatePost;
import post.post.upc.edu.pe.posts.command.application.events.PostCreatedEvent;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;
import java.math.BigDecimal;
import java.time.Instant;

@Aggregate
public class Post {
    @AggregateIdentifier
    private String postId;
    private String userId;
    private BigDecimal overdraftLimit;
    private String text;
    public Post(){}
    @CommandHandler
    public Post(CreatePost command){
        apply(
                new PostCreatedEvent(
                        command.getPostId(),
                        command.getUserId(),
                        command.getOverdraftLimit(),
                        command.getText()
                )
        );
    }
}
