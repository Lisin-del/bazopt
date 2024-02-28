package ru.lisin.bazopt.configuration.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.*;
import org.springframework.util.Assert;


import java.util.function.Supplier;

public class CSRFTokenRequestHandler extends CsrfTokenRequestAttributeHandler {
    private String csrfRequestAttributeName = "_csrf";

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            Supplier<CsrfToken> deferredCsrfToken
    ) {
        Assert.notNull(request, "request cannot be null");
        Assert.notNull(response, "response cannot be null");
        Assert.notNull(deferredCsrfToken, "deferredCsrfToken cannot be null");
        Supplier<CsrfToken> updatedCsrfToken = deferCsrfTokenUpdate(deferredCsrfToken);
        updatedCsrfToken.get().getToken();
        super.handle(request, response, updatedCsrfToken);
    }

    private Supplier<CsrfToken> deferCsrfTokenUpdate(Supplier<CsrfToken> csrfTokenSupplier) {
        return new CachedCsrfTokenSupplier(() -> {
            CsrfToken csrfToken = csrfTokenSupplier.get();
            Assert.state(csrfToken != null, "csrfToken supplier returned null");
            String updatedToken = csrfToken.getToken();
            return new DefaultCsrfToken(csrfToken.getHeaderName(), csrfToken.getParameterName(), updatedToken);
        });
    }

    private static final class CachedCsrfTokenSupplier implements Supplier<CsrfToken> {

        private final Supplier<CsrfToken> delegate;

        private CsrfToken csrfToken;

        private CachedCsrfTokenSupplier(Supplier<CsrfToken> delegate) {
            this.delegate = delegate;
        }

        @Override
        public CsrfToken get() {
            if (this.csrfToken == null) {
                this.csrfToken = this.delegate.get();
            }
            return this.csrfToken;
        }

    }
}
