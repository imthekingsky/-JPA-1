package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {
    // 경우에 따라서는 위임만 하는경우 Service 를 만들어야 하는지 고민할 필요가있다.(Controller -> Repository (Service 스킵))

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    @Transactional
    public void updateItem(Long itemId, UpdateBookDto bookDtoDto){
        Book findItem = (Book)itemRepository.findOne(itemId);
        findItem.changeBook(bookDtoDto);
//        findItem.setName(itemDto.getName());
//        findItem.setPrice(itemDto.getPrice());
//        findItem.setStockQuantity(itemDto.getStockQuantity());
//        findItem.setStockQuantity(itemDto.getAuthor());
//        findItem.setStockQuantity(itemDto.getIsbn());
    }


    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
