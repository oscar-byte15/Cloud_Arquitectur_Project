package post.post.upc.edu.pe.posts.command.application.validators;

import org.springframework.stereotype.Component;
import post.post.upc.edu.pe.common.application.Notification;
import post.post.upc.edu.pe.posts.command.application.dto.request.OpenPostRequest;
import post.post.upc.edu.pe.posts.command.application.infra.PostNumberRepository;

import java.math.BigDecimal;

@Component
public class OpenPostValidator {
    private final PostNumberRepository postNumberRepository;

    public OpenPostValidator(PostNumberRepository postNumberRepository) {
        this.postNumberRepository = postNumberRepository;
    }
    public Notification validate(OpenPostRequest openPostRequest){
        Notification notification = new Notification();
        String user= openPostRequest.getUserId();
        if (user.isEmpty()) {
            notification.addError("Post number is required");
        }
        String postId = openPostRequest.getUserId().trim();
        if(postId.isEmpty()){
            notification.addError("User id is required");
        }
        BigDecimal overdraftLimit = openPostRequest.getOverdraftLimit();
        String text = openPostRequest.getText();
        if(text.isEmpty()){
            notification.addError("Post text is required");
        }
        if (notification.hasErrors()) {
            return notification;
        }
        return notification;
    }
}
