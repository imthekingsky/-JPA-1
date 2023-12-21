package jpabook.jpashop.controller;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.UpdateBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 상품 등록 화면(GET)
    @GetMapping("/items/new")
    public String createForm(Model model){
        model.addAttribute("form",new BookForm());
        return "items/createItemForm";
    }

    // 상품 등록(POST)
    @PostMapping("/items/new")
    public String create(@Valid @ModelAttribute("form") BookForm form, BindingResult result){

        if(result.hasErrors()){ // 상품 이름 작성 필수, 공백 확인 검증
            return "items/createItemForm";
        }
        // 전달받은 상품 등록(상품명, 저자, ISBN) 앞뒤 공백 제거
        BookForm.trimSpaces(form);

        Book book = new Book();

        // 사실 set 보다는 Order 의 static 생성 메서드를 만들어주는게 좋다. ex) static Book createBook(...)
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());

        itemService.saveItem(book);
        return "redirect:/items";
    }

    // 상품 목록 조회
    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    // 상품 수정 화면
    @GetMapping("items/{itemId}/edit")
    public String updateItemForm(@PathVariable("itemId") Long itemId, Model model){
        Book item = (Book)itemService.findOne(itemId);

        BookForm form = new BookForm(); // Entity 대신 Form 을 보낸다
        form.setId(item.getId());
        form.setName(item.getName());
        form.setPrice(item.getPrice());
        form.setStockQuantity(item.getStockQuantity());
        form.setAuthor(item.getAuthor());
        form.setIsbn(item.getIsbn());

        model.addAttribute("form", form);
        return "items/updateItemForm";
    }

    @PostMapping("items/{itemId}/edit")
    public String updateItem(@PathVariable("itemId") Long itemId, @ModelAttribute("form") BookForm form){ // 사실 @PathVariable("itemId"),@ModelAttribute("form") 생략 가능

        // 사실 Id는 조심해야한다 조작 가능하기때문
        // 그래서 서비스 계층이든 어디선가 이 유저가 이 아이템에 대해서 권한이 있는지 확인이 필요하다.

        // Form 은 웹 계층에서만 쓰자고 잡았다
//        Book book = new Book(); // 이제는 Form 을 Entity(Book)으로 변환
//        book.setId(form.getId());
//        book.setName(form.getName());
//        book.setPrice(form.getPrice());
//        book.setStockQuantity(form.getStockQuantity());
//        book.setAuthor(form.getAuthor());
//        book.setIsbn(form.getIsbn());
//        itemService.saveItem(book);

        // 트랜잭션이 있는 서비스 계층에 식별자( id )와 변경할 데이터를 명확하게 전달.(파라미터 or dto)
        itemService.updateItem(itemId, new UpdateBookDto(form.getName(), form.getPrice(), form.getStockQuantity(), form.getAuthor(), form.getIsbn()));

        return "redirect:/items";
    }
    }

