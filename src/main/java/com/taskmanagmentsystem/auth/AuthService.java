package com.taskmanagmentsystem.auth;

import com.taskmanagmentsystem.auth.dto.LoginRequestDto;
import com.taskmanagmentsystem.auth.dto.LoginResponseDto;
import com.taskmanagmentsystem.auth.dto.RegisterRequestDto;
import com.taskmanagmentsystem.auth.dto.RegisterResponseDto;
import com.taskmanagmentsystem.config.JwtService;
import com.taskmanagmentsystem.employees.Employee;
import com.taskmanagmentsystem.employees.EmployeeRepository;
import com.taskmanagmentsystem.employees.EmployeeRole;
import com.taskmanagmentsystem.exceptions.BadRequestException;
import com.taskmanagmentsystem.exceptions.EmployeeNotFoundException;
import com.taskmanagmentsystem.token.Token;
import com.taskmanagmentsystem.token.TokenRepository;
import com.taskmanagmentsystem.token.TokenType;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<RegisterResponseDto> register(RegisterRequestDto registerRequestDto) {

        var employee = employeeRepository.findEmployeeByEmail(registerRequestDto.email());

        if (employee.isPresent()) throw new BadRequestException("Employee already exists.");

        saveEmployee(registerRequestDto);

        RegisterResponseDto registerResponseDto = RegisterResponseDto.builder()
                .firstname(registerRequestDto.firstname())
                .lastname(registerRequestDto.lastname())
                .email(registerRequestDto.email())
                .build();

        return ResponseEntity.ok(registerResponseDto);
    }

    public ResponseEntity<LoginResponseDto> login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.email(),
                        loginRequestDto.password()
                )
        );

        var employee = employeeRepository.findEmployeeByEmail(loginRequestDto.email())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee does not exists."));

        String accessToken = jwtService.generateToken(employee);
        String refreshToken = jwtService.generateRefreshToken(employee);

        saveToken(accessToken, employee);

        LoginResponseDto loginResponseDto = LoginResponseDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

        return ResponseEntity.ok(loginResponseDto);

    }

    private void saveEmployee(RegisterRequestDto registerRequestDto) {
        Employee employee = Employee.builder()
                .firstname(registerRequestDto.firstname())
                .lastname(registerRequestDto.lastname())
                .email(registerRequestDto.email())
                .password(passwordEncoder.encode(registerRequestDto.password()))
                .employeeRole(EmployeeRole.EMPLOYEE)
                .build();

        employeeRepository.save(employee);
    }

    private void saveToken(String accessToken, Employee employee) {
        Token token = Token.builder()
                .token(accessToken)
                .employee(employee)
                .expired(false)
                .tokenType(TokenType.BEARER)
                .revoked(false)
                .build();

        tokenRepository.save(token);
    }

    private <D, E> E convertDtoToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }


}
