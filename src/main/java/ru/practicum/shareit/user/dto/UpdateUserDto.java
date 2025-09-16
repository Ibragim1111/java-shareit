package ru.practicum.shareit.user.dto;


import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor
public class UpdateUserDto {
    private Long id;

    private String name;

    @Email(message = "Электронная почта должна содержать символ @")
    private String email;

}
