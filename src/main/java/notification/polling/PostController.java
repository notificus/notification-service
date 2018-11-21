package notification.polling;

import notification.service.message.EmailMessageService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PostController {
    @PostMapping("/notes")
     public boolean updateNote(@RequestBody Notes newNote) {
        EmailMessageService emailMessageService = new EmailMessageService();
        emailMessageService.sendMessage(newNote.getCip()+"@gmail.com",newNote.getClassSigil(), "Une nouvelle note pour " + newNote.getClassSigil() + " est disponible.");
        return true;
    }
}
