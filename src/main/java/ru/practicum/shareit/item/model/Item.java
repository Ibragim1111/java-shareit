package ru.practicum.shareit.item.model;

/**
 * TODO Sprint add-controllers.
 */


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.user.User;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor
public class Item {
    private Long id;
    private String name;
    private String description;
    private Boolean available; // статус доступности для аренды
    private User owner; // владелец вещи
    private Long requestId; // ID запроса, если вещь создана в ответ на запрос

    // Конструкторы, геттеры, сеттеры, equals, hashCode
}