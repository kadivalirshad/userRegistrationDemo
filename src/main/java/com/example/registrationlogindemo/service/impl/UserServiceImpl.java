package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.Address;
import com.example.registrationlogindemo.entity.Role;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.AddresRepository;
import com.example.registrationlogindemo.repository.RoleRepository;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AddresRepository addressRepository;

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AddresRepository addressRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.addressRepository = addressRepository;
	}

	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getFirstName() + " " + userDto.getLastName());
		user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
		// encrypt the password once we integrate spring security
		// user.setPassword(userDto.getPassword());
//        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Role role = roleRepository.findByName("ROLE_ADMIN");
		if (role == null) {
			role = checkRoleExist();
		}
		List<Address> listofAddress = new ArrayList();
		for (int i = 0; i < userDto.getAddress1().length; i++) {
			Address addr = new Address();
			addr.setAddress1(userDto.getAddress1()[i]);
			addr.setAddress2(userDto.getAddress2()[i]);
			addr.setCity(userDto.getCity()[i]);
			addr.setPincode(userDto.getPincode()[i]);
			addr.setState(userDto.getState()[i]);

			listofAddress.add(addr);
		}
		user.setAddress(listofAddress);
		user.setRoles(Arrays.asList(role));

		addressRepository.saveAll(listofAddress);
		userRepository.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<UserDto> findAllUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map((user) -> convertEntityToDto(user)).collect(Collectors.toList());
	}

	private UserDto convertEntityToDto(User user) {
		UserDto userDto = new UserDto();
		String[] name = user.getName().split(" ");
		userDto.setFirstName(name[0]);
		userDto.setLastName(name[1]);
		userDto.setEmail(user.getEmail());
		userDto.setId(user.getId());
		userDto.setPassword(user.getPassword());
		userDto.setAddress1(user.getAddress1());
		userDto.setAddress2(user.getAddress2());
		userDto.setCity(user.getCity());
		userDto.setPincode(user.getPincode());
		userDto.setState(user.getState());
		userDto.setMobile(user.getMobile());
		userDto.setAddress(user.getAddress());
		return userDto;
	}

	private Role checkRoleExist() {
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleRepository.save(role);
	}

	@Override
	public UserDto findById(Long id) {
		Optional<User> user = userRepository.findById(id);
		UserDto dto = convertEntityToDto(user.get());
		return dto;

	}

	@Override
	public void updateUser(UserDto userDto) {
		User user = new User();
		user.setName(userDto.getFirstName() + " " + userDto.getLastName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setId(userDto.getId());
		user.setMobile(userDto.getMobile());
		Role role = roleRepository.findByName("ROLE_ADMIN");
		if (role == null) {
			role = checkRoleExist();
		}
		List<Address> listofAddress = new ArrayList();
		for (int i = 0; i < userDto.getAddress1().length; i++) {
			Address addr = new Address();
			addr.setAddress1(userDto.getAddress1()[i]);
			addr.setAddress2(userDto.getAddress2()[i]);
			addr.setCity(userDto.getCity()[i]);
			addr.setPincode(userDto.getPincode()[i]);
			addr.setState(userDto.getState()[i]);
			if (userDto.getAddress_id()[i] != null) {
				addr.setId(userDto.getAddress_id()[i]);
			}

			listofAddress.add(addr);
		}
		user.setAddress(listofAddress);
		user.setRoles(Arrays.asList(role));

		addressRepository.saveAll(listofAddress);
		userRepository.save(user);
	}

	@Override
	public boolean deleteById(long id) {
		boolean flag = false;
		try {
			userRepository.deleteById(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
