package controller;

import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import settings.KeepData;
import util.Request;
    
    public class Relatorio {
        
        public void gerarRelatorio() throws IOException, DocumentException {
            
            Document doc = null;
            OutputStream os = null;
            Request request = new Request();

	        try {
                    
                    //fontes
                    Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                    Font fonte2 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                    Font fonte3 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
                    
	            //cria o documento tamanho A4, margens de 2,54cm
                    doc = new Document(PageSize.A4, 72, 72, 72, 72);

	            //cria a stream de saída
	            os = new FileOutputStream("Relatório.pdf");
	       
	            //associa a stream de saída ao
	            PdfWriter.getInstance(doc, os);

	            //abre o documento
	            doc.open();	 

                    //titulo
                    Paragraph p1 = new Paragraph("RELATÓRIO DE GESTÃO DE DECISÃO", fonte1);
                    p1.setAlignment(Element.ALIGN_CENTER);
                    p1.setSpacingAfter(20); 
                    doc.add(p1);
                    
	            String nomeOrganizacao = KeepData.getData("Organizacao.nome");
                    Paragraph p3 = new Paragraph("1. Organização: " + nomeOrganizacao, fonte2);
                    doc.add(p3);
                    
                    String nomeProblema = KeepData.getData("Problema.nome");
                    Paragraph p4 = new Paragraph("2. Problema: " + nomeProblema, fonte2);
                    doc.add(p4);
	 
	        } finally {
	            if (doc != null) {
	                //fechamento do documento
	                doc.close();
	            }
	            if (os != null) {
	               //fechamento da stream de saída
	               os.close();
	            }
	        }
	    }
	}