package notification.polling;

import notification.service.message.EmailMessageService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PullController {

    @PostMapping("/notes")
     public boolean updateNote(@RequestBody Notes newNote) {
        EmailMessageService emailMessageService = new EmailMessageService();
        emailMessageService.sendMessage("notificusUdes@gmail.com",newNote.getNoteType(), newNote.getCip()+"\n"+
                                        newNote.getNoteType()+"\n"+newNote.getCompetence());
        return true;
    }
}
