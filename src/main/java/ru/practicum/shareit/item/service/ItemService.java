package ru.practicum.shareit.item.service;

import ru.practicum.shareit.item.dto.UpdateItemDto;
import ru.practicum.shareit.item.model.Item;

import java.util.List;

public interface ItemService {
    Item createItem(Item item, Long ownerId);

    Item getItemById(Long id);

    List<Item> getItemsByOwnerId(Long ownerId);

    Item updateItem(Long itemId, UpdateItemDto item, Long ownerId);

    List<Item> searchItems(String text);
}
