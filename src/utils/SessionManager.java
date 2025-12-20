package utils;

import models.User;
import exceptions.AuthenticationException;

/**
 * Session Manager - Manages user sessions
 */
public class SessionManager {
    
    private static SessionManager instance;
    private User currentUser;
    
    private SessionManager() {
        currentUser = null;
    }
    
    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }
    
    /**
     * Login user
     */
    public void login(String username, String password) throws AuthenticationException {
        DatabaseManager dbManager = DatabaseManager.getInstance();
        User user = dbManager.getUserByUsername(username);
        
        if (user == null) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Invalid username or password");
        }
        
        if (!user.isActive()) {
            throw new AuthenticationException("User account is inactive");
        }
        
        currentUser = user;
    }
    
    /**
     * Logout current user
     */
    public void logout() {
        currentUser = null;
    }
    
    /**
     * Check if user is logged in
     */
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    
    /**
     * Get current logged in user
     */
    public User getCurrentUser() {
        return currentUser;
    }
    
    /**
     * Check if current user is admin
     */
    public boolean isAdmin() {
        return currentUser != null && currentUser.getRole() == User.UserRole.ADMIN;
    }
}
