package com.main.service;

import com.main.model.AccountStatus;
import com.main.model.Role;
import com.main.model.UserAccount;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserManagementService {

    private final List<UserAccount> users = new ArrayList<>();

    public UserManagementService() {
        users.add(new UserAccount(1L, "Nguyen Van Admin", "admin@gmail.com", Role.ADMIN, AccountStatus.ACTIVE));
        users.add(new UserAccount(2L, "Tran Thi Manager", "manager@gmail.com", Role.MANAGER, AccountStatus.ACTIVE));
        users.add(new UserAccount(3L, "Le Van Staff", "staff@gmail.com", Role.STAFF, AccountStatus.LOCKED));
        users.add(new UserAccount(4L, "Pham Van Accountant", "accountant@gmail.com", Role.ACCOUNTANT, AccountStatus.ACTIVE));
    }

    public List<UserAccount> getAllUsers() {
        return users;
    }

    public UserAccount getUserById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    public void addUser(UserAccount userAccount) {
        long nextId = users.stream()
                .map(UserAccount::getId)
                .max(Comparator.naturalOrder())
                .orElse(0L) + 1;
        userAccount.setId(nextId);
        users.add(userAccount);
    }

    public void updateUser(Long id, UserAccount updatedUser) {
        UserAccount existingUser = getUserById(id);
        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setStatus(updatedUser.getStatus());
    }
}
