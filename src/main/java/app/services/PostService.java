package app.services;

import app.entities.*;
import app.exceptions.AccountNotFoundException;
import app.exceptions.PostNotFoundException;
import app.repositories.CommentsRepository;
import app.repositories.PostsRepository;
import app.repositories.ProfileRepository;
import app.repositories.neo4j.Neo4jProfileRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PostService {
    private final PostsRepository postsRepository;
    private final CommentsRepository commentsRepository;
    private final Neo4jProfileRepository neo4jProfileRepository;
    private final ProfileRepository profileRepository;

    public PostService(PostsRepository postsRepository, CommentsRepository commentsRepository, Neo4jProfileRepository neo4jProfileRepository, ProfileRepository profileRepository) {
        this.postsRepository = postsRepository;
        this.commentsRepository = commentsRepository;
        this.neo4jProfileRepository = neo4jProfileRepository;
        this.profileRepository = profileRepository;
    }

    public Optional<Post> findPost(Long id) {
        return postsRepository.findById(id);
    }

    public Comment createComment(String content, Account author, Long postId) throws PostNotFoundException {
        Comment comment = new Comment();
        comment.setAuthor(author);
        comment.setContent(content);
        comment.setPublishedAt(LocalDateTime.now());

        Optional<Post> post = postsRepository.findById(postId);
        comment.setPost(post.orElseThrow(PostNotFoundException::new));

        commentsRepository.save(comment);

        post.get().getComments().add(comment);
        postsRepository.save(post.get());

        return comment;
    }

    public void create(Post post, Account author) {
        post.setAuthor(author);
        post.setPublishedAt(LocalDateTime.now());
        postsRepository.save(post);
    }

    public List<Post> all(Account account) {
        List<Post> posts;
        if (account.getType().equals("HUMAN")) {
            final Profile profile = profileRepository.findById(account.getId()).orElseThrow(AccountNotFoundException::new);
            final List<Post> collect = profile.getInterests().stream().map(Company::getPosts).flatMap(List::stream).collect(Collectors.toList());
            final Stream<Neo4jProfile> circle = neo4jProfileRepository.findCircleWithDepth1(profile.getId());
            final Collection<Long> ids = circle.map(Neo4jProfile::getProfileId).collect(Collectors.toList());
            final List<Profile> people = profileRepository.findAllById(ids);
            posts = people.stream().map(Profile::getPosts).flatMap(List::stream).collect(Collectors.toList());
            final List<Post> mine = profile.getPosts();
            posts.addAll(collect);
            posts.addAll(mine);
        } else {
            posts = postsRepository.findAllOrderByPublishedAtDesc();
        }
        return posts;
//        return postsRepository.findAllOrderByPublishedAtDesc();
    }

}
