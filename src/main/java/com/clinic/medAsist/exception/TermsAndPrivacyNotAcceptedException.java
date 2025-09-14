package com.clinic.medAsist.exception;

public class TermsAndPrivacyNotAcceptedException extends RuntimeException {
    public TermsAndPrivacyNotAcceptedException(String message) {
        super(message);
    }
}
