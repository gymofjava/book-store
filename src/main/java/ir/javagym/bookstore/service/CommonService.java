package ir.javagym.bookstore.service;

import ir.javagym.bookstore.aspect.exception.BadRequestException;

public class CommonService {
    public static void idExistenceException(Long id) {
        throw new BadRequestException("Don't send id");
    }
}
