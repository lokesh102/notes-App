package com.notesApp.notes.App.NotesApp.aspects;
import com.google.common.util.concurrent.RateLimiter;
import com.notesApp.notes.App.NotesApp.aspects.exceptions.RateLimitExceededException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RateLimitingAspect {

    private final RateLimiter rateLimiter = RateLimiter.create(10.0); // 10 requests per second

    @Before("@annotation(rateLimited)")
    public void beforeRequest(RateLimited rateLimited) throws RateLimitExceededException {
        if (!rateLimiter.tryAcquire()) {
            throw new RateLimitExceededException("Rate limit exceeded");
        }
    }
}

