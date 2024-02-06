package com.seyed.flight_reservation.utilities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.Date;


@Component //to generate bean of this during runtime
public class PdfGenerator {
    private static final Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
        Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);

    public void generatePdf(String filePath, String name, String emailId, Long phone, String operatingAirlines, LocalDate departureDate, String departureCity, String arrivalCity) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            addTitleAndTable(document,name, emailId,phone,operatingAirlines,departureDate,departureCity,arrivalCity);

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTitleAndTable(
            Document document, String name, String emailId, Long phone, String operatingAirlines, LocalDate departureDate, String departureCity, String arrivalCity
    )throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Flight Booking Details", catFont));
        preface.add(new Paragraph("Ticket generated at - "+new Date(), smallBold));
        preface.setAlignment(Element.ALIGN_CENTER);

        document.add(preface);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("Passenger Details",smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        table.addCell("Passenger name");
        table.addCell(name);
        table.addCell("Email Id");
        table.addCell(emailId);
        table.addCell("Phone Number");
        table.addCell(String.valueOf(phone));

        document.add(table);
        document.add(Chunk.NEWLINE);

        PdfPTable table1= new PdfPTable(2);
        table1.setWidthPercentage(100);
        PdfPCell c2 = new PdfPCell(new Phrase("Flight Details",smallBold));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        c2.setColspan(2);
        table1.addCell(c2);

        table1.addCell("Operating Airlines");
        table1.addCell(operatingAirlines);
        table1.addCell("Departure City");
        table1.addCell(departureCity);
        table1.addCell("Departure Date");
        table1.addCell(String.valueOf(departureDate));
        table1.addCell("Arrival City");
        table1.addCell(arrivalCity);

        document.add(table1);

        document.close();
    }
}