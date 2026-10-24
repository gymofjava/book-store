package ir.javagym.bookstore.utility;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;


public class CommonUtility {
    public static Pageable getPagination(HttpServletRequest request) {
        return (Pageable) request.getSession().getAttribute("pageable");
    }
}
