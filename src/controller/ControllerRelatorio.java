package controller;

import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import static com.itextpdf.text.zugferd.checkers.basic.MeasurementUnitCode.MM;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import model.Acessar;
import model.Historico;
import settings.Constant;
import settings.Facade;
import settings.KeepData;
import util.Request;
    
    public class ControllerRelatorio extends PdfPageEventHelper {
        
        Facade facade = Facade.getInstance();
        private Request request;
        private List<Request> requestList;
        private final ControllerProblema controllerProblema = new ControllerProblema();
        private final ControllerTarefas controllerTarefas = new ControllerTarefas();
        private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();
        private final ControllerAcessar controllerAcessar = new ControllerAcessar();
        private final ControllerCriterios controllerCriterios = new ControllerCriterios();
        protected PdfTemplate total;     
        protected BaseFont helv;
        protected PdfContentByte canvas;
        
        public void addHistoricoRelatorio(int idProblema) {
            try {
                Historico historico = new Historico();
                historico.setDescricao("Relatório foi Gerado.");
                historico.setUsuarioNome(KeepData.getData("Usuario.nome"));
                historico.setTipo(Constant.FUC_RELATORIO); 
                historico.setCreated(new Date());
                historico.setModified(new Date());
                historico.setIdProblema(facade.initializeJpaProblema().findProblema(idProblema));

                facade.initializeHistorico().create(historico);
            } catch (Exception e) {
            }
        } 
        
        //rodapé
        public void onEndPage(PdfWriter writer, Document document) {
		try {
			Rectangle page = document.getPageSize();
                        
			PdfPTable foot = new PdfPTable(1);
			PdfPCell cell = new PdfPCell();
			DateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			cell = new PdfPCell(new Phrase(sf.format(data)));
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(0);
			foot.addCell(cell);
			foot.setTotalWidth(page.getWidth() - document.leftMargin()
					- document.rightMargin());
			foot.writeSelectedRows(0, -1, document.leftMargin(), document
					.bottomMargin(), writer.getDirectContent());
                        
                        PdfContentByte canvas = writer.getDirectContent();
                        Rectangle rect = new Rectangle(36, 36, 559, 806);
                        rect.setBorder(Rectangle. BOX);
                        rect.setBorderWidth(1);
                        canvas.rectangle(rect);
                        
                        canvas.saveState();
                        try {
                            String txt = "Página "+writer.getPageNumber();
                            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);

                            float txtBase = document.left();
                            float txtSize = bf.getWidthPoint(txt, 8);
                            float adj = bf.getWidthPoint("0", 80);

                            canvas.beginText();
                            canvas.setFontAndSize(bf, 8);

                            canvas.setTextMatrix(document.right() - txtSize - adj, txtBase);
                            canvas.showText(txt);

                            canvas.endText();
                        } catch (DocumentException | IOException e) { 
                            e.printStackTrace();
                        }  
                        canvas.restoreState();
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}
        
        //rodapé
//        public void onEndPage(PdfWriter w, Document d) {
//        PdfContentByte canvas = w.getDirectContent();
//        canvas.saveState();
//            try {
//                String txt = "Página "+w.getPageNumber();
//                BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
//
//                float txtBase = d.left();
//                float txtSize = bf.getWidthPoint(txt, 8);
//                float adj = bf.getWidthPoint("0", 80);
//
//                canvas.beginText();
//                canvas.setFontAndSize(bf, 8);
//
//                canvas.setTextMatrix(d.right() - txtSize - adj, txtBase);
//                canvas.showText(txt);
//
//                canvas.endText();
//            } catch (DocumentException | IOException e) { 
//                e.printStackTrace();
//            }  
//            canvas.restoreState();
//        }
        
        public void gerarRelatorio() throws IOException, DocumentException {
            
            Document doc = null;
            OutputStream os = null;
            Request request = new Request();

	        try {
                    
                    //fontes
                    Font fonte1 = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                    Font fonte2 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                    Font fonte3 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                    Font fonte4 = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
                    
	            //cria o documento tamanho A4, margens de 2,54cm
                    doc = new Document(PageSize.A4, 72, 72, 72, 72);

	            //cria a stream de saída
	            os = new FileOutputStream("Relatório.pdf");
	       
	            //associa a stream de saída ao
	            PdfWriter write = PdfWriter.getInstance(doc, os);
	            
                    write.setPageEvent( this );
                    
                    //abre o documento
	            doc.open();	         
                    
                      //criar linha
//                    doc.add(new Paragraph("Before dotted line"));
//                    LineSeparator separator = new LineSeparator();
//                    separator.setPercentage(59500f / 523f);
//                    Chunk linebreak = new Chunk(separator);
//                    doc.add(linebreak);
//                    doc.add(new Paragraph("After dotted line"));
                    
                      //criar retangulo ao redor da página
//                    PdfContentByte canvas = write.getDirectContent();
//                    Rectangle rect = new Rectangle(36, 36, 559, 806);
//                    rect.setBorder(Rectangle.BOX);
//                    rect.setBorderWidth(2);
//                    canvas.rectangle(rect);
                    
                    //titulo
                    Paragraph p1 = new Paragraph("RELATÓRIO TOMADA DE DECISÃO", fonte1);
                    p1.setAlignment(Element.ALIGN_CENTER);
                    p1.setSpacingAfter(20); 
                    doc.add(p1);
                    
	            String nomeOrganizacao = KeepData.getData("Organizacao.nome");
                    Paragraph p3 = new Paragraph("Organização: " + nomeOrganizacao, fonte2);
                    p3.setAlignment(Element.ALIGN_CENTER);
                    p3.setSpacingAfter(20);
                    doc.add(p3);
                    
                    Paragraph p4 = new Paragraph();
                    LineSeparator separator = new LineSeparator();
                    separator.setPercentage(60500f / 523f);
                    Chunk linebreak = new Chunk(separator);
                    doc.add(linebreak);                    
                    p4.add(new Chunk("1. Problema: " , fonte3));
                    p4.setIndentationLeft(12);
                    doc.add(new Paragraph(p4));
                    doc.add(linebreak);
                    
                    String nomeProblema = KeepData.getData("Problema.nome");
                    Paragraph p300 = new Paragraph();                  
                    p300.add(new Chunk("Nome: " , fonte3));
                    p300.add(new Chunk(nomeProblema, fonte4));
                    p300.setIndentationLeft(12);
                    doc.add(new Paragraph(p300));
                   
                    request = controllerProblema.findProblemaById(Integer.parseInt(KeepData.getData("Problema.id")));
                    String propositoProblema = request.getData("Problema.proposito");
                    Paragraph p5 = new Paragraph();
                    p5.add(new Chunk("Propósito: " , fonte3));
                    p5.add(new Chunk(propositoProblema, fonte4));
                    p5.setIndentationLeft(12);
                    doc.add(new Paragraph(p5));
            
                    String planejamentoProblema = request.getData("Problema.planejamento");
                    Paragraph p6 = new Paragraph();
                    p6.add(new Chunk("Planejamento: " , fonte3));
                    p6.add(new Chunk(planejamentoProblema, fonte4));
                    p6.setIndentationLeft(12);
                    doc.add(new Paragraph(p6));
                    
                    String contextoProblema = request.getData("Problema.contexto");
                    Paragraph p7 = new Paragraph();
                    p7.add(new Chunk("Contexto/Cenário: " , fonte3));
                    p7.add(new Chunk(contextoProblema, fonte4));
                    p7.setIndentationLeft(12);
                    doc.add(new Paragraph(p7));
                  
                    Paragraph p8 = new Paragraph("Envolvidos:", fonte3);
                    p8.setIndentationLeft(12);
                    doc.add(p8);
                    
                    //for para listar todos os envolvidos no problema
                    List<Acessar> envolvidosList = controllerAcessar.findUsuariosPerfisByIdProblema(request);
                    for (int i = 0; i < envolvidosList.size(); i++) { 
                        String nomeEnvolvidos = envolvidosList.get(i).getUsuario().getNome();
                        String perfilEnvolvidos = envolvidosList.get(i).getPerfil().getNome();
                        Paragraph p0 = new Paragraph();
                        p0.add(new Chunk("Nome: ", fonte3));
                        p0.add(new Chunk(nomeEnvolvidos, fonte4));
                        p0.add(new Chunk("   Perfil: ", fonte3));
                        p0.add(new Chunk(perfilEnvolvidos, fonte4));
                        p0.setIndentationLeft(14);
                        doc.add(new Paragraph(p0));
                    }
                    
                    Paragraph p9 = new Paragraph(" ");
                    doc.add(p9);
                    
                    Paragraph p10 = new Paragraph();
                    LineSeparator separator1 = new LineSeparator();
                    separator1.setPercentage(60500f / 523f);
                    Chunk linebreak1 = new Chunk(separator1);
                    doc.add(linebreak1);                    
                    p10.add(new Chunk("2. Tarefas: " , fonte3));
                    p10.setIndentationLeft(12);
                    doc.add(new Paragraph(p10));
                    doc.add(linebreak1);
                    
//                    for pra listar todas as tarefas
                    int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
                    List<Request> tarefasList = controllerTarefas.listTarefasByProjeto(idProblema);   
                    for (int i = 0; i < tarefasList.size(); i++) {
                        String nomeTarefa = tarefasList.get(i).getData("Tarefa.nome"); 
                        Paragraph p11 = new Paragraph();
                        p11.add(new Chunk("Nome: " , fonte3));
                        p11.add(new Chunk(nomeTarefa, fonte4));
                        p11.setIndentationLeft(12);
                        doc.add(new Paragraph(p11));
                        
                        String descricaoTarefa = tarefasList.get(i).getData("Tarefa.descricao");
                        Paragraph p12 = new Paragraph();
                        p12.add(new Chunk("Descrição: " , fonte3));
                        p12.add(new Chunk(descricaoTarefa, fonte4));
                        p12.setIndentationLeft(12);
                        doc.add(new Paragraph(p12));
                        
                        String prazoTarefa = tarefasList.get(i).getData("Tarefa.data");
                        Paragraph p13 = new Paragraph();
                        p13.add(new Chunk("Prazo: " , fonte3));
                        p13.add(new Chunk(prazoTarefa, fonte4));
                        p13.setIndentationLeft(12);
                        doc.add(new Paragraph(p13));
                        
                        String complexidadeTarefa = tarefasList.get(i).getData("Tarefa.marcador");
                        Paragraph p14 = new Paragraph();
                        p14.add(new Chunk("Complexidade: " , fonte3));
                        p14.add(new Chunk(complexidadeTarefa, fonte4));
                        p14.setIndentationLeft(12);
                        doc.add(new Paragraph(p14));
                        
                        Paragraph p15;
                        String statusTarefa = tarefasList.get(i).getData("Tarefa.feito");
                        switch (statusTarefa) {
                            case "true":
                                p15 = new Paragraph();
                                p15.add(new Chunk("Status: " , fonte3));
                                p15.add(new Chunk("Concluída", fonte4));
                                p15.setIndentationLeft(12);
                                doc.add(new Paragraph(p15));
                                break;
                            case "false":
                                p15 = new Paragraph();
                                p15.add(new Chunk("Status: " , fonte3));
                                p15.add(new Chunk("Em andamento", fonte4));
                                p15.setIndentationLeft(12);
                                doc.add(new Paragraph(p15));
                                break;
                        }
                                           
                        Paragraph p16 = new Paragraph(" ");
                        doc.add(p16);
                    } 
                    
                    Paragraph p17 = new Paragraph();
                    LineSeparator separator2 = new LineSeparator();
                    separator2.setPercentage(60500f / 523f);
                    Chunk linebreak2 = new Chunk(separator2);
                    doc.add(linebreak2);                    
                    p17.add(new Chunk("3. Alternativas de Solução: " , fonte3));
                    p17.setIndentationLeft(12);
                    doc.add(new Paragraph(p17));
                    doc.add(linebreak2);
                    
                    //for para listar todas as alternativas de solução
                    List<Request> alternativasList = controllerAlternativa.listAlternativasByProblema(); 
                    for (int i = 0; i < alternativasList.size(); i++) {
                       
                        String nomeAlternativa = alternativasList.get(i).getData("Alternativa.nome");
                        Paragraph p18 = new Paragraph();
                        p18.add(new Chunk("Nome: " , fonte3));
                        p18.add(new Chunk(nomeAlternativa, fonte4));
                        p18.setIndentationLeft(12);
                        doc.add(new Paragraph(p18));
                        
                        String descricaoAlternativa = alternativasList.get(i).getData("Alternativa.descricao");
                        Paragraph p19 = new Paragraph();
                        p19.add(new Chunk("Descrição: " , fonte3));
                        p19.add(new Chunk(descricaoAlternativa, fonte4));
                        p19.setIndentationLeft(12);
                        doc.add(new Paragraph(p19));
                        
                        String custoAlternativa = alternativasList.get(i).getData("Alternativa.estimativaCusto");
                        Paragraph p20 = new Paragraph();
                        p20.add(new Chunk("Estimativa de Custo: " , fonte3));
                        p20.add(new Chunk(custoAlternativa, fonte4));
                        p20.setIndentationLeft(12);
                        doc.add(new Paragraph(p20));
                       
                        String tempoAlternativa = alternativasList.get(i).getData("Alternativa.estimativaTempo");
                        Paragraph p21 = new Paragraph();
                        p21.add(new Chunk("Estimativa de Tempo: " , fonte3));
                        p21.add(new Chunk(tempoAlternativa, fonte4));
                        p21.setIndentationLeft(12);
                        doc.add(new Paragraph(p21));
                        
                        Paragraph p22 = new Paragraph(" ");
                        doc.add(p22);
                    }
                    
                    Paragraph p23 = new Paragraph();
                    LineSeparator separator3 = new LineSeparator();
                    separator3.setPercentage(60500f / 523f);
                    Chunk linebreak3 = new Chunk(separator3);
                    doc.add(linebreak3);                    
                    p23.add(new Chunk("4. Critérios de Avaliação: " , fonte3));
                    p23.setIndentationLeft(12);
                    doc.add(new Paragraph(p23));
                    doc.add(linebreak3);
                    
                    //for para listar os critérios do problema
                    List<Request> criteriosList = controllerCriterios.listCriteirosByProjeto(idProblema);
                    for (int i = 0; i < criteriosList.size(); i++) {
                        String nomeCriterio = criteriosList.get(i).getData("Criterio.nome");
                        Paragraph p24 = new Paragraph();
                        p24.add(new Chunk("Nome: " , fonte3));
                        p24.add(new Chunk(nomeCriterio, fonte4));
                        p24.setIndentationLeft(12);
                        doc.add(new Paragraph(p24));
                        
                        String pesoCriterio = criteriosList.get(i).getData("Criterio.peso");
                        Paragraph p25 = new Paragraph();
                        p25.add(new Chunk("Peso: " , fonte3));
                        p25.add(new Chunk(pesoCriterio, fonte4));
                        p25.setIndentationLeft(12);
                        doc.add(new Paragraph(p25));
                        
                        String justificativaCriterio = criteriosList.get(i).getData("Criterio.justificativa");
                        Paragraph p26 = new Paragraph();
                        p26.add(new Chunk("Justificativa para o peso: " , fonte3));
                        p26.add(new Chunk(justificativaCriterio, fonte4));
                        p26.setIndentationLeft(12);
                        doc.add(new Paragraph(p26));
                        
                        //for para listar notas e valores
//                        int idCriterios = Integer.parseInt(KeepData.getData("Criterio.id"));
//                        List<Request> notasList = controllerCriterios.listNotasByCriterio(idCriterios);
//                        for (int j = 0; j < notasList.size(); j++) {
//                        
//                            String notaCriterio = notasList.get(j).getData("Nota.nome");
//                            Paragraph p27 = new Paragraph();
//                            p27.add(new Chunk("Nota: " , fonte3));
//                            p27.add(new Chunk(notaCriterio, fonte4));
//                            p27.setIndentationLeft(12);
//                            doc.add(new Paragraph(p27));
//
//                            String valorCriterio = notasList.get(j).getData("Nota.valor");
//                            Paragraph p28 = new Paragraph();
//                            p28.add(new Chunk("Valor: " , fonte3));
//                            p28.add(new Chunk(valorCriterio, fonte4));
//                            p28.setIndentationLeft(12);
//                            doc.add(new Paragraph(p28));
//                        }
                    }
                      
                    addHistoricoRelatorio(Integer.parseInt(KeepData.getData("Problema.id")));
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