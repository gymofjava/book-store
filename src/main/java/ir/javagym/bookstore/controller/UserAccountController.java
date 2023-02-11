package ir.javagym.bookstore.controller;

import io.swagger.annotations.ApiOperation;
import ir.javagym.bookstore.aspect.pagination.Pagination;
import ir.javagym.bookstore.entity.model.UserAccount;
import ir.javagym.bookstore.entity.dto.Response;
import ir.javagym.bookstore.service.UserAccountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import static ir.javagym.bookstore.utility.CommonUtility.getPagination;

@RestController
@RequestMapping("api/user")
public class UserAccountController {
    private UserAccountService userAccountService;

    public UserAccountController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping
    Response<Long> addUserAccount(@RequestBody @Valid UserAccount userAccount) {
        return userAccountService.addUserAccount(userAccount);
    }

    @PutMapping
    Response<Long> updateUserAccount(@RequestBody @Valid UserAccount userAccount) {
        return userAccountService.updateUserAccount(userAccount);
    }

    @DeleteMapping("{id}")
    Response<?> deleteUserAccountById(@PathVariable Long id) {
        return userAccountService.deleteUserAccountById(id);
    }

    @GetMapping("{id}")
    Response<UserAccount> getUserAccountById(@PathVariable Long id) {
        return userAccountService.getUserAccountById(id);
    }

    @Pagination
    @GetMapping
    @ApiOperation(value = "getAllUserAccounts (for pagination add 'page' & 'npp' parameters to header. by default page = 0 & npp = 10)")
    Response<Page<UserAccount>> getAllUserAccounts(HttpServletRequest request) {
        Pageable pagination = getPagination(request);
        return userAccountService.getAllUserAccounts(pagination);
    }
}
