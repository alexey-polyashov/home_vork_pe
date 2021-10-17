package ru.polyan.homework.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.polyan.homework.dto.UserDto;
import ru.polyan.homework.exceptions.ResourceNotFoundException;
import ru.polyan.homework.models.Role;
import ru.polyan.homework.models.User;
import ru.polyan.homework.repositories.UsersRepository;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public Optional<User> findByUsername(String username) {
        return usersRepository.findByUsername(username);
    }
    public Optional<User> findById(Long userId) {
        return usersRepository.findById(userId);
    }

    public Optional<User> findByEmail(String email) {
        return usersRepository.findByEmail(email);
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void createUser(Map<String, String> userData){

        User user = new User();
        List<Role> roles = new ArrayList<>();
        Role baseRole = roleService.findName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Dont found base user role!"));
        roles.add(baseRole);

        user.setRoles(roles);
        String fldValue = userData.get("address");
        if(fldValue!=null){
            user.setAddress(fldValue);
        }
        fldValue = userData.get("email");
        if(fldValue!=null){
            user.setEmail(fldValue);
        }
        fldValue = userData.get("firstname");
        if(fldValue!=null){
            user.setFirstname(fldValue);
        }
        fldValue = userData.get("lastname");
        if(fldValue!=null){
            user.setLastname(fldValue);
        }
        fldValue = userData.get("username");
        if(fldValue!=null){
            user.setUsername(fldValue);
        }
        fldValue = userData.get("phone");
        if(fldValue!=null){
            user.setPhone(fldValue);
        }
        fldValue = userData.get("password");
        if(fldValue!=null){
            String pwd = passwordEncoder.encode(fldValue);
            user.setPassword(pwd);
        }

        fldValue = userData.get("birthday");
        if(fldValue!=null){
            LocalDate date = LocalDate.parse(fldValue);
            user.setBirthday(date);
        }

        usersRepository.save(user);

    }


}