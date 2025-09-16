package ru.practicum.shareit.item.dto;


import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.User;


public class ItemMapper {
    public static ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getRequestId()
        );
    }

    public static Item toItem(NewItemDto itemDto, User owner) {
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                owner,
                itemDto.getRequestId()
        );
    }

    public static Item updateItemFields(Item existingItem, UpdateItemDto request) {

        Item newUpdate = new Item();

        return newUpdate.toBuilder()
                .id(existingItem.getId())
                .name(request.getName() != null ? request.getName() : existingItem.getName())
                .description(request.getDescription() != null ? request.getDescription() : existingItem.getDescription())
                .available(request.getAvailable() != null ? request.getAvailable() : existingItem.getAvailable())
                .owner(existingItem.getOwner())
                .requestId(request.getRequestId() != null ? request.getRequestId() : existingItem.getRequestId())
                .build();
    }

}
