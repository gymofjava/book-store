package ir.javagym.bookstore.service;

import ir.javagym.bookstore.aspect.exception.BadRequestException;
import ir.javagym.bookstore.aspect.exception.CrudException;
import ir.javagym.bookstore.aspect.exception.DataNotFoundException;
import ir.javagym.bookstore.dao.UserAccountRepository;
import ir.javagym.bookstore.entity.model.UserAccount;
import ir.javagym.bookstore.entity.dto.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserAccountService {
    private UserAccountRepository userAccountRepository;

    public UserAccountService(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public Response<Long> addUserAccount(UserAccount userAccount) {
        if (userAccount.getId() != null)
            throw new BadRequestException("Don't send id");
        userAccount.setDates();
        UserAccount savedUserAccount = userAccountRepository.save(userAccount);
        if (savedUserAccount.getId() == null)
            throw new CrudException("Saving UserAccount has a problem");
        return Response.<Long>builder().message("UserAccount added").body(savedUserAccount.getId()).build();
    }

    public Response<Long> updateUserAccount(UserAccount userAccount) {
        if (userAccount.getId() == null)
            throw new BadRequestException("You must Send id attribute for updating UserAccount");
        Optional<UserAccount> optionalUser = userAccountRepository.findById(userAccount.getId());
        UserAccount foundUser = optionalUser.orElseThrow(() -> new DataNotFoundException("This UserAccount not exist"));

        userAccount.setCreateDate(foundUser.getCreateDate());
        userAccount.setUpdateDate(LocalDateTime.now());

        UserAccount updatedUserAccount = userAccountRepository.save(userAccount);
        return Response.<Long>builder().message("UserAccount updated").body(updatedUserAccount.getId()).build();
    }

    public Response<?> deleteUserAccountById(Long id) {
        userAccountRepository.deleteById(id);
        return Response.builder().message("UserAccount deleted").build();
    }

    public Response<UserAccount> getUserAccountById(Long id) {
        Optional<UserAccount> result = userAccountRepository.findById(id);
        UserAccount userAccount = result.orElseThrow(() -> new DataNotFoundException("This UserAccount not exist"));
        return Response.<UserAccount>builder().message("UserAccount founded").body(userAccount).build();
    }

    public Response<Page<UserAccount>> getAllUserAccounts(Pageable pagination) {
        Page<UserAccount> userAccountPage = userAccountRepository.findAll(pagination);
        if (userAccountPage.isEmpty())
            throw new DataNotFoundException("Page of UserAccounts not found");
        return Response.<Page<UserAccount>>builder().message("Page of UserAccounts founded").body(userAccountPage).build();
    }
}
