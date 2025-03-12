package ru.prj.services;

import jakarta.servlet.http.HttpSession;

public class UserSessionService {
    private static final String FULL_NAME_KEY = "fullName";
    private static final String EMAIL_KEY = "email";

    private final HttpSession session;

    public UserSessionService(HttpSession session) {
        this.session = session;
    }

    public void saveStudentData(String fullName, String email) {
        session.setAttribute(FULL_NAME_KEY, fullName);
        session.setAttribute(EMAIL_KEY, email);
    }

    public String getFullName() {
        return (String) session.getAttribute(FULL_NAME_KEY);
    }

    public String getEmail() {
        return (String) session.getAttribute(EMAIL_KEY);
    }

    public  void invalidate() {
        session.invalidate();
    }
}
