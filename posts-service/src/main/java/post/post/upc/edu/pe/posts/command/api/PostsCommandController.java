package post.post.upc.edu.pe.posts.command.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import post.post.upc.edu.pe.common.api.ApiController;
import post.post.upc.edu.pe.common.application.Notification;
import post.post.upc.edu.pe.common.application.Result;
import post.post.upc.edu.pe.posts.command.application.dto.request.OpenPostRequest;
import post.post.upc.edu.pe.posts.command.application.dto.response.OpenPostResponse;
import post.post.upc.edu.pe.posts.command.application.service.PostApplicationService;

@RestController
@RequestMapping("/posts")
@Tag(name="Posts")
public class PostsCommandController {
    private final CommandGateway commandGateway;
    private final PostApplicationService postApplicationService;
    public PostsCommandController(CommandGateway commandGateway, PostApplicationService postApplicationService) {
        this.commandGateway = commandGateway;
        this.postApplicationService = postApplicationService;
    }
    @PostMapping("")
    public ResponseEntity<Object> open(@Validated @RequestBody OpenPostRequest openPostRequest) {
        try {
            Result<OpenPostResponse, Notification> result = postApplicationService.open(openPostRequest);
            if (result.isSuccess()) {
                return ApiController.created(result.getSuccess());
            }
            return ApiController.error(result.getFailure().getErrors());
        } catch(Exception e) {
            return ApiController.serverError();
        }
    }
}
