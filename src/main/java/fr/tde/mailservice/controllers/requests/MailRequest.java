package fr.tde.mailservice.controllers.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {

    private String[] to;
    private String[] bcc;
    private String[] cc;
    private String subject;
    private String text;
    private String pathToAttachment;

}
