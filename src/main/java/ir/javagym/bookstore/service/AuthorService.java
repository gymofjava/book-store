package ir.javagym.bookstore.service;

import ir.javagym.bookstore.aspect.exception.BadRequestException;
import ir.javagym.bookstore.aspect.exception.CrudException;
import ir.javagym.bookstore.aspect.exception.DataNotFoundException;
import ir.javagym.bookstore.dao.AuthorRepository;
import ir.javagym.bookstore.entity.model.Author;
import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Response<Long> addAuthor(Author author) {
        if (author.getId() != null)
            throw new BadRequestException("Don't send id");
        author.setDates();
        Author savedAuthor = authorRepository.save(author);
        if (savedAuthor.getId() == null)
            throw new CrudException("Saving Author has a problem");
        return Response.<Long>builder().message("Author added").body(savedAuthor.getId()).build();
    }

    public Response<Long> updateAuthor(Author author) {
        if (author.getId() == null)
            throw new BadRequestException("You must Send id attribute for updating Author");
        Optional<Author> optionalAuthor = authorRepository.findById(author.getId());
        Author foundAuthor = optionalAuthor.orElseThrow(() -> new DataNotFoundException("This Author not exist"));

        author.setCreateDate(foundAuthor.getCreateDate());
        author.setUpdateDate(LocalDateTime.now());

        Author updatedAuthor = authorRepository.save(author);
        return Response.<Long>builder().message("Author updated").body(updatedAuthor.getId()).build();
    }

    public Response<?> deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
        return Response.builder().message("Author deleted").build();
    }

    public Response<Author> getAuthorById(Long id) {
        Optional<Author> result = authorRepository.findById(id);
        Author author = result.orElseThrow(() -> new DataNotFoundException("This Author not exist"));
        return Response.<Author>builder().message("Author founded").body(author).build();
    }

    public Response<Page<Author>> getAllAuthors(Pageable pagination) {
        Page<Author> authorPage = authorRepository.findAll(pagination);
        if (authorPage.isEmpty())
            throw new DataNotFoundException("Page of Authors not found");
        return Response.<Page<Author>>builder().message("Page of Authors founded").body(authorPage).build();
    }
}
