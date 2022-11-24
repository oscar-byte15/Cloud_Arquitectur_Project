package post.post.upc.edu.pe.posts.command.application.service;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Component;
import post.post.upc.edu.pe.common.application.Notification;
import post.post.upc.edu.pe.common.application.Result;
import post.post.upc.edu.pe.common.application.ResultType;
import post.post.upc.edu.pe.posts.command.application.commands.CreatePost;
import post.post.upc.edu.pe.posts.command.application.dto.request.OpenPostRequest;
import post.post.upc.edu.pe.posts.command.application.dto.response.OpenPostResponse;
import post.post.upc.edu.pe.posts.command.application.infra.PostNumberRepository;
import post.post.upc.edu.pe.posts.command.application.validators.OpenPostValidator;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class PostApplicationService {
    private final CommandGateway commandGateway;
    private final PostNumberRepository paymentNumberRepository;
    private final OpenPostValidator openPostValidator;
    public PostApplicationService(CommandGateway commandGateway, PostNumberRepository paymentNumberRepository, OpenPostValidator openPostValidator) {
        this.commandGateway = commandGateway;
        this.paymentNumberRepository = paymentNumberRepository;
        this.openPostValidator = openPostValidator;
    }
    public Result<OpenPostResponse, Notification> open(OpenPostRequest openPostRequest) throws Exception {
        Notification notification = this.openPostValidator.validate(openPostRequest);
        if (notification.hasErrors()) {
            return Result.failure(notification);
        }
        String postId = UUID.randomUUID().toString();
        CreatePost createPost = new CreatePost(
                postId,
                openPostRequest.getUserId(),
                openPostRequest.getOverdraftLimit(),
                openPostRequest.getText()
        );
        CompletableFuture<Object> future = commandGateway.send(createPost);
        CompletableFuture<ResultType> futureResult = future.handle((ok, ex) -> (ex != null) ? ResultType.FAILURE : ResultType.SUCCESS);
        ResultType resultType = futureResult.get();
        if (resultType == ResultType.FAILURE) {
            throw new Exception();
        }
        OpenPostResponse openPostResponse = new OpenPostResponse(
                createPost.getPostId(),
                createPost.getUserId(),
                createPost.getOverdraftLimit(),
                createPost.getText()
        );
        return Result.success(openPostResponse);
    }
}
