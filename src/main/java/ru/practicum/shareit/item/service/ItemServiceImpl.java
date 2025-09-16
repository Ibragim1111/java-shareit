package ru.practicum.shareit.item.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exceptions.exception.NotFoundException;
import ru.practicum.shareit.item.dto.ItemMapper;
import ru.practicum.shareit.item.dto.UpdateItemDto;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.storage.ItemRepository;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserService userService;

    @Override
    public Item createItem(Item item, Long ownerId) {

        User owner = userService.getUserById(ownerId);
        item.setOwner(owner);

        return itemRepository.save(item);
    }

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item not found with id: " + id));
    }

    @Override
    public List<Item> getItemsByOwnerId(Long ownerId) {
        userService.getUserById(ownerId); // Проверяем, что пользователь существует
        return itemRepository.findByOwnerId(ownerId);
    }

    @Override
    public Item updateItem(Long itemId, UpdateItemDto itemUpdate, Long ownerId) {
        Item existingItem = getItemById(itemId);

        Item newItem = ItemMapper.updateItemFields(existingItem, itemUpdate);
        // Проверяем, что пользователь является владельцем
        if (!existingItem.getOwner().getId().equals(ownerId)) {
            throw new NotFoundException("User is not the owner of the item");
        }


        return itemRepository.update(newItem);
    }

    @Override
    public List<Item> searchItems(String text) {
        if (text == null || text.isBlank()) {
            return List.of();
        }
        return itemRepository.search(text);
    }
}