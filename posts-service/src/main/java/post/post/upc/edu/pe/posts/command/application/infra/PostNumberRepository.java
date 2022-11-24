package post.post.upc.edu.pe.posts.command.application.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostNumberRepository extends JpaRepository<PostNumber,String> {
}
