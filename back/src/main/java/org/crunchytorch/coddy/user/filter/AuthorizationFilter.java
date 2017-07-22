package org.crunchytorch.coddy.user.filter;

import javax.ws.rs.NameBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * this annotation can be apply on method, class, interface, enum, see {@link ElementType}.
 * This annotation allow to say to JAX-RS runtime that a specific filter or interceptor will be executed only for a specific resource method.
 * for more information, please see {@link NameBinding}
 */
@NameBinding
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizationFilter {
}
