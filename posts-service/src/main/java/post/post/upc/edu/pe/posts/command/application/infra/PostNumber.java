package post.post.upc.edu.pe.posts.command.application.infra;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class PostNumber {
    @Id
    @Column(length=25)
    public String text;
    public String userId;

    public PostNumber(){

    }
    public PostNumber(String text, String userId){
        this.text = text;
        this.userId = userId;
    }
}
