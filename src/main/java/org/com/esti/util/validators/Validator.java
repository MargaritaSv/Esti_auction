package org.com.esti.util.validators;

public interface Validator {
    <M> boolean isValid(M model);
}
