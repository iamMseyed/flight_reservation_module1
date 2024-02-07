package com.seyed.flight_reservation.utilities;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.seyed.flight_reservation.entity.Reservation;
import org.springframework.stereotype.Component;
import java.io.FileOutputStream;
import java.util.Date;


@Component //to generate bean of this during runtime
public class PdfGenerator {
    private static final Font catFont = new Font(Font.FontFamily.HELVETICA, 18,
        Font.BOLD);
    private static final Font smallBold = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);

    private static final Font thankYou = new Font(Font.FontFamily.TIMES_ROMAN, 10,
            Font.ITALIC);

    public void generateItinerary(Reservation reservation, String filePath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open(); //open the document
             generateTable(reservation,document); //call generateTable and returns table
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static PdfPTable generateTable(Reservation reservation, Document document) throws DocumentException {
        Paragraph preface = new Paragraph();

        preface.add(new Paragraph("Flight Booking Details", catFont));
        preface.add(new Paragraph("Ticket generated at - "+new Date(), smallBold));
        preface.setAlignment(Element.ALIGN_CENTER);

        document.add(preface);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);

        PdfPTable table = putPassengerDetails(reservation); //add passenger details
        document.add(table);

        document.add(Chunk.NEWLINE);

        PdfPTable table1 = putFlightDetails(reservation); //add flight details
        document.add(table1);

       return table;
    }

    private static PdfPTable putPassengerDetails(Reservation reservation) {
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("Passenger Details",smallBold));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setColspan(2);
        table.addCell(c1);

        table.addCell("Passenger name");
        table.addCell(reservation.getPassenger().getFirstName()+" "+ reservation.getPassenger().getMiddleName()+" "+ reservation.getPassenger().getLastName());
        table.addCell("Email Id");
        table.addCell(reservation.getPassenger().getEmail());
        table.addCell("Phone Number");
        table.addCell(String.valueOf(reservation.getPassenger().getPhone()));
        return table;
    }

    private static PdfPTable putFlightDetails(Reservation reservation) {
        PdfPTable table1= new PdfPTable(2);
        table1.setWidthPercentage(100);
        PdfPCell c2 = new PdfPCell(new Phrase("Flight Details",smallBold));
        c2.setHorizontalAlignment(Element.ALIGN_CENTER);
        c2.setColspan(2);
        table1.addCell(c2);

        table1.addCell("Operating Airlines");
        table1.addCell(reservation.getFlight().getOperatingAirlines());
        table1.addCell("Departure Date");
        table1.addCell(String.valueOf(reservation.getFlight().getDateOfDeparture()));
        table1.addCell("Departure City");
        table1.addCell(reservation.getFlight().getDepartureCity());
        table1.addCell("Arrival City");
        table1.addCell(reservation.getFlight().getArrivalCity());


        return table1;
    }
}