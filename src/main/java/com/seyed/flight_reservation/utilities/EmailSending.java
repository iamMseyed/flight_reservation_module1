package com.seyed.flight_reservation.utilities;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EmailSending {
    private final JavaMailSender javaMailSender;

    public EmailSending(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendItinerary(String toAddress, String fName,String filePath){ //send email with attachment
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true); // Initialize in multipart mode
            mimeMessageHelper.setTo(toAddress);
            mimeMessageHelper.setSubject("Flight Booking Details");
            mimeMessageHelper.setText("Dear "+fName+", "+ """
                            thank you for booking with us! Your flight reservation pdf is attached with this email. \n \n
                            Please keep this safe until you reach your destination."""+
                            "\n \nHappy Journey."
                    );
            mimeMessageHelper.addAttachment("booking_"+fName, new File(filePath));
            javaMailSender.send(mimeMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}