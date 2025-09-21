package ru.practicum.shareit.item.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.User;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private Long id;

    @NotBlank(message = "Имя не должно быть пустое")
    private String name;

    @NotBlank(message = "Описание не должно быть пустое")
    private String description;

    @NotNull(message = "Доступ должен быть указан")
    private Boolean available;

    private User owner;

    private Long requestId;

}
