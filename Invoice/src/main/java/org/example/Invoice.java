package org.example;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;

import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.layout.LayoutContext;
import com.itextpdf.layout.layout.LayoutResult;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.renderer.IRenderer;

import java.io.*;


public class Invoice {
    public static void main(String[] args) throws IOException {
        String path = "result.pdf";

        PdfFont introScriptDemo = PdfFontFactory.createFont("font/introscriptdemo-medium.woff2", "UTF8", true);
        PdfWriter pdfWriter = new PdfWriter(path);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        String imgPath = "images/kk senza sfondo.png";
        ImageData imagedata = ImageDataFactory.create(imgPath);
        Image image = new Image(imagedata);
        image.setHeight((float) 75);

        pdfDocument.setDefaultPageSize(PageSize.A4);
        float x = pdfDocument.getDefaultPageSize().getWidth() / 2;
        float y = pdfDocument.getDefaultPageSize().getHeight() / 2;
        System.out.println("x= " + x);
        System.out.println("y= " + y);

        Document document = new Document(pdfDocument);
        float threecol = 190f;
        float colonnaUno = 85f;
        float colonnaPerTitolo = 216f;
        float threeColumnWidth2[] = {colonnaUno, colonnaPerTitolo, threecol};
        float twocol = 285f;
        float twocol150 = 550f; //435f start point
        float twoColumnWidth[] = {twocol150, twocol};
        float threeColumnWidth[] = {threecol, threecol, threecol};
        float fullWidth[] = {threecol * 3};
        com.itextpdf.kernel.colors.Color verdePistacchio = new DeviceRgb(172, 216, 114 );
        com.itextpdf.kernel.colors.Color rosaLogo = new DeviceRgb(242, 125, 164);
        Color verdeScuro = new DeviceRgb(141, 184, 85);

        Table headerTable = new Table(threeColumnWidth2);
        headerTable.addCell(new Cell().add(image).setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add(new Paragraph("KindKaribe").setFont(introScriptDemo).setFontSize(38).setMarginTop(2)).setBorder(Border.NO_BORDER));
        headerTable.addCell(new Cell().add( new Paragraph("KindKaribe,\nVia dei Gigli 88, Caserta (CS),\nTel: 0818734028\nkindkaribe@kindkaribe.com")).setBorder(Border.NO_BORDER));
        document.add(headerTable);

        //linea di separazione
        LineSeparator ls = new LineSeparator(new SolidLine(1f));
        ls.setMarginTop((float) 2.0);
        document.add(ls);

        //descrizione fattura
        Table descrizione = new Table(twoColumnWidth);
        descrizione.setMarginTop((float) 10.0);
        descrizione.addCell(new Cell().add(new Paragraph(("Fattura")).setBold().setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(verdePistacchio));
        descrizione.addCell(new Cell().add(new Paragraph(("Destinatario")).setTextAlignment(TextAlignment.CENTER)).setBold().setBackgroundColor(verdePistacchio));
        descrizione.addCell(new Cell().add(new Paragraph("Fattura numero: 1\nData: 20/11/2001").setMarginLeft(2).setMarginTop(1)));
        descrizione.addCell(new Cell().add(new Paragraph("Spettabile Mario Rossi\nTDSLLD00E18C129Y\n3887868300").setMarginLeft(2).setMarginTop(1)));
        document.add(descrizione);

        //linea di separazione
        ls.setMarginTop((float) 10.0);
        document.add(ls);

        //prodotti acquistati
        Table prodotti = new Table(threeColumnWidth);
        prodotti.setMarginTop(10);
        //intestazione tabella prodotti
        prodotti.addCell(new Cell().add(new Paragraph(("Prodotto")).setBold().setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(verdePistacchio));
        prodotti.addCell(new Cell().add(new Paragraph(("Quantità")).setBold().setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(verdePistacchio));
        prodotti.addCell(new Cell().add(new Paragraph(("Costo")).setBold().setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(verdePistacchio));

        prodotti.addCell(new Cell().add(new Paragraph("Torta Mimosa").setMarginLeft(2).setMarginTop(1)));
        prodotti.addCell(new Cell().add(new Paragraph("1").setMarginLeft(2).setMarginTop(1)));
        prodotti.addCell(new Cell().add(new Paragraph("€ 88,00").setMarginLeft(2).setMarginTop(1)));

        prodotti.addCell(new Cell().add(new Paragraph("Torta Mimosa").setMarginLeft(2).setMarginTop(1)));
        prodotti.addCell(new Cell().add(new Paragraph("1").setMarginLeft(2).setMarginTop(1)));
        prodotti.addCell(new Cell().add(new Paragraph("€ 88,00").setMarginLeft(2).setMarginTop(1)));

        prodotti.addCell(new Cell().add(new Paragraph("Torta Mimosa").setMarginLeft(2).setMarginTop(1)));
        prodotti.addCell(new Cell().add(new Paragraph("1").setMarginLeft(2).setMarginTop(1)));
        prodotti.addCell(new Cell().add(new Paragraph("€ 88,00").setMarginLeft(2).setMarginTop(1)));

        document.add(prodotti);

        //linea di separazione
        ls.setMarginTop((float) 20.0);
        document.add(ls);

        //DETTAGLI FISCALI
        Table dettagliFiscali = new Table(twoColumnWidth);
        dettagliFiscali.setMarginTop(10);

        dettagliFiscali.addCell(new Cell().add(new Paragraph(("Metodo Pagamento")).setBold().setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(verdePistacchio));
        dettagliFiscali.addCell(new Cell().add(new Paragraph(("Costo Totale")).setBold().setTextAlignment(TextAlignment.CENTER)).setBackgroundColor(verdePistacchio));

        dettagliFiscali.addCell(new Cell().add(new Paragraph("Mastercard").setMarginLeft(2).setMarginTop(1)));
        dettagliFiscali.addCell(new Cell().add(new Paragraph("Imponibile: € 33,00\nSpedizione: € 2,00\nSconto: € 0,00\nIVA: 10%\n").setMarginLeft(2).setMarginTop(1)));
        document.add(dettagliFiscali);

        Table totale = new Table(fullWidth);
        totale.addCell(new Cell().add(new Paragraph("Totale: € 300,00").setBold().setFontSize(16).setTextAlignment(TextAlignment.RIGHT).setMarginRight(25)).setBackgroundColor(verdeScuro));
        document.add(totale);

        //note
        Table note = new Table(fullWidth);
        note.setFixedPosition(20, 0 , threecol*3);
        note.addCell(new Cell().add(new Paragraph("Grazie per averci preferito!")).setBorder(Border.NO_BORDER));
        document.add(note);

        document.close();
    }
}
