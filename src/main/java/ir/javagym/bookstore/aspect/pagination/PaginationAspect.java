package ir.javagym.bookstore.aspect.pagination;


import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


@Aspect
@Component
public class PaginationAspect {

    @Pointcut("@annotation(ir.javagym.bookstore.aspect.pagination.Pagination)")
    private void customPagingAnnotation() {
    }

    @Around("ir.javagym.bookstore.aspect.pagination.PaginationAspect.customPagingAnnotation()")
    public Object doSomething(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest req = getRequest();
        try {
            int page = Integer.parseInt(req.getHeader("page"));
            int npp = Integer.parseInt(req.getHeader("npp"));
            Pageable pageable = PageRequest.of(page, npp, Sort.Direction.DESC, "updateDate");
            req.getSession().setAttribute("pageable", pageable);
        } catch (Exception e) {
            Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "updateDate");
            req.getSession().setAttribute("pageable", pageable);
        } finally {
            return pjp.proceed();
        }
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return sra.getRequest();
    }

}