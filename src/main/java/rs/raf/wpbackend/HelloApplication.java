package rs.raf.wpbackend;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import rs.raf.wpbackend.repositories.categories.CategoryRepository;
import rs.raf.wpbackend.repositories.categories.MySqlCategoryRepository;
import rs.raf.wpbackend.repositories.comments.CommentRepository;
import rs.raf.wpbackend.repositories.comments.MySqlCommentRepository;
import rs.raf.wpbackend.repositories.posts.MySqlPostRepository;
import rs.raf.wpbackend.repositories.posts.PostRepository;
import rs.raf.wpbackend.repositories.posttag.MySqlPostTagRepository;
import rs.raf.wpbackend.repositories.posttag.PostTagRepository;
import rs.raf.wpbackend.repositories.tags.MySqlTagRepository;
import rs.raf.wpbackend.repositories.tags.TagRepository;
import rs.raf.wpbackend.repositories.user.MySqlUserRepository;
import rs.raf.wpbackend.repositories.user.UserRepository;
import rs.raf.wpbackend.services.*;

import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
public class HelloApplication extends ResourceConfig {
    public HelloApplication() {
        // Ukljucujemo validaciju
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);

        // Definisemo implementacije u dependency container-u
        AbstractBinder binder = new AbstractBinder() {
            @Override
            protected void configure() {
                this.bind(MySqlPostRepository.class).to(PostRepository.class).in(Singleton.class);
                this.bind(MySqlCommentRepository.class).to(CommentRepository.class).in(Singleton.class);
                this.bind(MySqlUserRepository.class).to(UserRepository.class).in(Singleton.class);
                this.bind(MySqlCategoryRepository.class).to(CategoryRepository.class).in(Singleton.class);
                this.bind(MySqlTagRepository.class).to(TagRepository.class).in(Singleton.class);
                this.bind(MySqlPostTagRepository.class).to(PostTagRepository.class).in(Singleton.class);


                this.bindAsContract(CommentService.class);
                this.bindAsContract(PostService.class);
                this.bindAsContract(UserService.class);
                this.bindAsContract(CategoryService.class);
                this.bindAsContract(TagService.class);
                this.bindAsContract(PostTagService.class);
            }
        };
        register(binder);


        // Ucitavamo resurse
        packages("rs.raf.wpbackend");
    }
}



