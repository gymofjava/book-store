package ir.javagym.bookstore.service;

import ir.javagym.bookstore.aspect.exception.BadRequestException;
import ir.javagym.bookstore.aspect.exception.CrudException;
import ir.javagym.bookstore.aspect.exception.DataNotFoundException;
import ir.javagym.bookstore.dao.PublisherRepository;
import ir.javagym.bookstore.entity.model.Publisher;
import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PublisherService {

    private PublisherRepository publisherRepository;

    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Response<Long> addPublisher(Publisher publisher) {
        if (publisher.getId() != null)
            throw new BadRequestException("Don't send id");
        publisher.setDates();
        Publisher savedPublisher = publisherRepository.save(publisher);
        if (savedPublisher.getId() == null)
            throw new CrudException("Saving Publisher has a problem");
        return Response.<Long>builder().message("Publisher added").body(savedPublisher.getId()).build();
    }

    public Response<Long> updatePublisher(Publisher publisher) {
        if (publisher.getId() == null)
            throw new BadRequestException("You must Send id attribute for updating Publisher");
        Optional<Publisher> optionalPublisher = publisherRepository.findById(publisher.getId());
        Publisher foundPublisher = optionalPublisher.orElseThrow(() -> new DataNotFoundException("This Publisher not exist"));

        publisher.setCreateDate(foundPublisher.getCreateDate());
        publisher.setUpdateDate(LocalDateTime.now());

        Publisher updatedPublisher = publisherRepository.save(publisher);
        return Response.<Long>builder().message("Publisher updated").body(updatedPublisher.getId()).build();
    }

    public Response<?> deletePublisherById(Long id) {
        publisherRepository.deleteById(id);
        return Response.builder().message("Publisher deleted").build();
    }

    public Response<Publisher> getPublisherById(Long id) {
        Optional<Publisher> result = publisherRepository.findById(id);
        Publisher publisher = result.orElseThrow(() -> new DataNotFoundException("This Publisher not exist"));
        return Response.<Publisher>builder().message("Publisher founded").body(publisher).build();
    }

    public Response<Page<Publisher>> getAllPublishers(Pageable pagination) {
        Page<Publisher> publisherPage = publisherRepository.findAll(pagination);
        if (publisherPage.isEmpty())
            throw new DataNotFoundException("Page of Publishers not found");
        return Response.<Page<Publisher>>builder().message("Page of Publishers founded").body(publisherPage).build();
    }
}
