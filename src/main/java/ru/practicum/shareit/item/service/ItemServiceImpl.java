package ru.practicum.shareit.item.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
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
    public Item updateItem(Long itemId, Item item, Long ownerId) {
        Item existingItem = getItemById(itemId);

        // Проверяем, что пользователь является владельцем
        if (!existingItem.getOwner().getId().equals(ownerId)) {
            throw new NotFoundException("User is not the owner of the item");
        }

        if (item.getName() != null) {
            existingItem.setName(item.getName());
        }
        if (item.getDescription() != null) {
            existingItem.setDescription(item.getDescription());
        }
        if (item.getAvailable() != null) {
            existingItem.setAvailable(item.getAvailable());
        }

        return itemRepository.update(existingItem);
    }

    @Override
    public List<Item> searchItems(String text) {
        return itemRepository.search(text);
    }
}
