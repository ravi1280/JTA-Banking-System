package lk.jiat.ee.web.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.authentication.mechanism.http.AutoApplySession;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@AutoApplySession
@ApplicationScoped
public class AuthMechanism implements HttpAuthenticationMechanism {
    @Inject
    private IdentityStore identityStore;

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest request,
                                                HttpServletResponse response,
                                                HttpMessageContext context) throws AuthenticationException {

        AuthenticationParameters authenticationParameters =context.getAuthParameters();
        System.out.println("Authentication parameters: " + authenticationParameters);
        if(authenticationParameters.getCredential() !=null){
            CredentialValidationResult result = identityStore.validate(authenticationParameters.getCredential());
            if(result.getStatus() == CredentialValidationResult.Status.VALID){
                return context.notifyContainerAboutLogin(result);
            }else {
                return AuthenticationStatus.SEND_FAILURE;
            }
        }

        if(context.isProtected() && context.getCallerPrincipal() != null){
            try {
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            } catch (IOException e) {
                throw new RuntimeException("Redirect to login ", e);
            }
        }

        return context.doNothing() ;
    }
}
