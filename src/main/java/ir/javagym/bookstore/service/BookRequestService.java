package ir.javagym.bookstore.service;

import ir.javagym.bookstore.aspect.exception.BadRequestException;
import ir.javagym.bookstore.aspect.exception.CrudException;
import ir.javagym.bookstore.aspect.exception.DataNotFoundException;
import ir.javagym.bookstore.dao.BookRequestRepository;
import ir.javagym.bookstore.entity.model.BookRequest;
import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookRequestService {

    private BookRequestRepository bookRequestRepository;

    public BookRequestService(BookRequestRepository bookRequestRepository) {
        this.bookRequestRepository = bookRequestRepository;
    }

    public Response<Long> addBookRequest(BookRequest bookRequest) {
        if (bookRequest.getId() != null)
            throw new BadRequestException("Don't send id");
        bookRequest.setDates();
        BookRequest savedBookRequest = bookRequestRepository.save(bookRequest);
        if (savedBookRequest.getId() == null)
            throw new CrudException("Saving BookRequest has a problem");
        return Response.<Long>builder().message("BookRequest added").body(savedBookRequest.getId()).build();
    }

    public Response<?> deleteBookRequestById(Long id) {
        bookRequestRepository.deleteById(id);
        return Response.builder().message("BookRequest deleted").build();
    }

    public Response<BookRequest> getBookRequestById(Long id) {
        Optional<BookRequest> result = bookRequestRepository.findById(id);
        BookRequest bookRequest = result.orElseThrow(() -> new DataNotFoundException("This BookRequest not exist"));
        return Response.<BookRequest>builder().message("BookRequest founded").body(bookRequest).build();
    }

    public Response<Page<BookRequest>> getAllBookRequests(Pageable pagination) {
        Page<BookRequest> bookRequestPage = bookRequestRepository.findAll(pagination);
        if (bookRequestPage.isEmpty())
            throw new DataNotFoundException("Page of BookRequests not found");
        return Response.<Page<BookRequest>>builder().message("Page of BookRequests founded").body(bookRequestPage).build();
    }
}
