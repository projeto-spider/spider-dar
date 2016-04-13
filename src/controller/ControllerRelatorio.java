package controller;

import com.itextpdf.text.Chunk;
import java.io.FileOutputStream;
import java.io.OutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
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
        private final ControllerAvaliacao controllerAvaliacao = new ControllerAvaliacao();
        private final ControllerDecisao controllerDecisao = new ControllerDecisao();
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
                        //rodapé com data e número de página
			Rectangle page = document.getPageSize();
                        
                        String txt = "Página "+writer.getPageNumber();
                        Font fonte = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
			PdfPTable foot = new PdfPTable(2);
			PdfPCell cell1 = new PdfPCell();
                        PdfPCell cell2 = new PdfPCell();
			DateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = new Date();
			cell1 = new PdfPCell(new Phrase(sf.format(data), fonte));
                        cell2 = new PdfPCell(new Phrase(txt, fonte));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell1.setBorder(0);
                        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell2.setBorder(0);
			foot.addCell(cell1);
                        foot.addCell(cell2);
			foot.setTotalWidth(page.getWidth() - document.leftMargin()
					- document.rightMargin());
			foot.writeSelectedRows(0, -1, document.leftMargin(), document
					.bottomMargin(), writer.getDirectContent());
                        
                        //criar retangulo ao redor da página
//                        PdfContentByte canvas = writer.getDirectContent();
//                        Rectangle rect = new Rectangle(36, 36, 559, 806);
//                        rect.setBorder(Rectangle.BOX);
//                        rect.setBorderWidth(1);
//                        canvas.rectangle(rect);
                        
		} catch (Exception e) {
			throw new ExceptionConverter(e);
		}
	}
        
        public void gerarRelatorio() throws IOException, DocumentException {
            
            Document doc = null;
            OutputStream os = null;
            Request request = new Request();

	        try {
                    
                    //fontes
                    Font fonte1 = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
                    Font fonte2 = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
                    Font fonte3 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
                    Font fonte4 = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL);
                    
	            //cria o documento tamanho A4, margens de 2,54cm
                    doc = new Document(PageSize.A4, 35, 35, 35, 35);

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
                    
                    //titulo
                    Paragraph p1 = new Paragraph("RELATÓRIO TOMADA DE DECISÃO", fonte1);
                    p1.setAlignment(Element.ALIGN_CENTER);
                    p1.setSpacingAfter(10); 
                    doc.add(p1);
                    
	            String nomeOrganizacao = KeepData.getData("Organizacao.nome");
                    Paragraph p3 = new Paragraph("Organização: " + nomeOrganizacao, fonte2);
                    p3.setAlignment(Element.ALIGN_CENTER);
                    p3.setSpacingAfter(15);
                    doc.add(p3);
                    
//                    marca texto
//                    LineSeparator separator = new LineSeparator();
//                    separator.setPercentage(52000f / 523f);
//                    Chunk linebreak = new Chunk(separator);
//                    doc.add(linebreak);                    
//                    Chunk chunk = new Chunk("1.PROBLEMA", fonte3);
//                    chunk.setBackground(BaseColor.RED);
//                    Paragraph p4 = new Paragraph(chunk);
//                    p4.setIndentationLeft(12);
//                    doc.add(p4);
//                    doc.add(linebreak);
                    
                    PdfPTable table = new PdfPTable(1);
                    table.setWidthPercentage(100);
                    table.setHorizontalAlignment(Element.ALIGN_LEFT);
                    PdfPCell cell;
                    cell = new PdfPCell();
                    cell.setBorder(PdfPCell.BOX);
                    cell.addElement(new Paragraph("1.PROBLEMA", fonte3));
                    table.addCell(cell);
                    doc.add(table);
                    
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
                    
                    PdfPTable t2 = new PdfPTable(new float[]{0.40f, 0.50f, 0.10f});
                    //table.setWidthPercentage(30);
                    t2.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                    PdfPCell header10 = new PdfPCell(new Paragraph("Nome:", fonte3));
                    PdfPCell header20 = new PdfPCell(new Paragraph("Perfil:", fonte3));
                    PdfPCell header30 = new PdfPCell(new Paragraph(" ", fonte3));
                    header10.setBorder(PdfPCell.NO_BORDER);
                    header20.setBorder(PdfPCell.NO_BORDER);
                    header30.setBorder(PdfPCell.NO_BORDER);
                    t2.addCell(header10);
                    t2.addCell(header20);
                    t2.addCell(header30);
                    List<Acessar> envolvidosList = controllerAcessar.findUsuariosPerfisByIdProblema(request);
                    for (int i = 0; i < envolvidosList.size(); i++) {
                        String nomeEnvolvidos = envolvidosList.get(i).getUsuario().getNome();
                        String perfilEnvolvidos = envolvidosList.get(i).getPerfil().getNome();
                        t2.addCell(new Phrase(nomeEnvolvidos, fonte4));
                        t2.addCell(new Phrase(perfilEnvolvidos, fonte4));  
                        t2.addCell(new Phrase(" "));
                    }
                    t2.setSpacingBefore(6);
                    t2.setSpacingAfter(6);
                    doc.add(t2);

                    //TAREFAS
                    
                        PdfPTable table1 = new PdfPTable(1);
                        table1.setWidthPercentage(100);
                        table1.setHorizontalAlignment(Element.ALIGN_LEFT);
                        PdfPCell cell1;
                        cell1 = new PdfPCell();
                        cell1.setBorder(PdfPCell.BOX);
                        cell1.addElement(new Paragraph("2.TAREFAS", fonte3));
                        table1.addCell(cell1);
                        table1.setSpacingBefore(6);
                        doc.add(table1);

    //                    for pra listar todas as tarefas
                        int idProblema = Integer.parseInt(KeepData.getData("Problema.id"));
                        List<Request> tarefasList = controllerTarefas.listTarefasByProjeto(idProblema);   
                        for (int i = 0; i < tarefasList.size(); i++) {
                            String nomeTarefa = tarefasList.get(i).getData("Tarefa.nome"); 
                            Paragraph p11 = new Paragraph();
                            p11.add(new Chunk("2." + (i+1) + ".Nome: " , fonte3));
                            p11.add(new Chunk(nomeTarefa, fonte4));
                            p11.setIndentationLeft(12);
                            doc.add(new Paragraph(p11));

                            String descricaoTarefa = tarefasList.get(i).getData("Tarefa.descricao");
                            Paragraph p12 = new Paragraph();
                            p12.add(new Chunk("Descrição: " , fonte3));
                            p12.add(new Chunk(descricaoTarefa, fonte4));
                            p12.setIndentationLeft(29);
                            doc.add(new Paragraph(p12));

                            String prazoTarefa = tarefasList.get(i).getData("Tarefa.data");
                            Paragraph p13 = new Paragraph();
                            p13.add(new Chunk("Prazo: " , fonte3));
                            p13.add(new Chunk(prazoTarefa, fonte4));
                            p13.setIndentationLeft(29);
                            doc.add(new Paragraph(p13));

                            Paragraph p14;
                            String complexidadeTarefa = tarefasList.get(i).getData("Tarefa.marcador");
                            switch (complexidadeTarefa) {
                                case "1":
                                    p14 = new Paragraph();
                                    p14.add(new Chunk("Complexidade: " , fonte3));
                                    p14.add(new Chunk("Trivial", fonte4));
                                    p14.setIndentationLeft(29);
                                    doc.add(new Paragraph(p14));
                                    break;
                                case "2":
                                    p14 = new Paragraph();
                                    p14.add(new Chunk("Complexidade: " , fonte3));
                                    p14.add(new Chunk("Pequeno", fonte4));
                                    p14.setIndentationLeft(29);
                                    doc.add(new Paragraph(p14));
                                    break;
                                case "3":
                                    p14 = new Paragraph();
                                    p14.add(new Chunk("Complexidade: " , fonte3));
                                    p14.add(new Chunk("Médio", fonte4));
                                    p14.setIndentationLeft(29);
                                    doc.add(new Paragraph(p14));
                                    break;
                                case "4":
                                    p14 = new Paragraph();
                                    p14.add(new Chunk("Complexidade: " , fonte3));
                                    p14.add(new Chunk("Grande", fonte4));
                                    p14.setIndentationLeft(29);
                                    doc.add(new Paragraph(p14));
                                    break;
                            }

                            Paragraph p15;
                            String statusTarefa = tarefasList.get(i).getData("Tarefa.feito");
                            switch (statusTarefa) {
                                case "true":
                                    p15 = new Paragraph();
                                    p15.add(new Chunk("Status: " , fonte3));
                                    p15.add(new Chunk("Concluída", fonte4));
                                    p15.setIndentationLeft(29);
                                    p15.setSpacingAfter(4);
                                    doc.add(new Paragraph(p15));
                                    break;
                                case "false":
                                    p15 = new Paragraph();
                                    p15.add(new Chunk("Status: " , fonte3));
                                    p15.add(new Chunk("Em andamento", fonte4));
                                    p15.setIndentationLeft(29);
                                    p15.setSpacingAfter(4);
                                    doc.add(new Paragraph(p15));
                                    break;
                            }

//                            Paragraph p16 = new Paragraph(" ");
//                            doc.add(p16);
                        } 
                    
                    //ALTERNATIVAS DE SOLUÇÃO       
                        
                        PdfPTable table2 = new PdfPTable(1);
                        table2.setWidthPercentage(100);
                        table2.setHorizontalAlignment(Element.ALIGN_LEFT);
                        PdfPCell cell2;
                        cell2 = new PdfPCell();
                        cell2.setBorder(PdfPCell.BOX);
                        cell2.addElement(new Paragraph("3.ALTERNATIVAS DE SOLUÇÃO", fonte3));
                        table2.addCell(cell2);
                        table2.setSpacingBefore(6);
                        doc.add(table2);

                        //for para listar todas as alternativas de solução
                        List<Request> alternativasList = controllerAlternativa.listAlternativasByProblema(); 
                        for (int i = 0; i < alternativasList.size(); i++) {

                            String nomeAlternativa = alternativasList.get(i).getData("Alternativa.nome");
                            Paragraph p18 = new Paragraph();
                            p18.add(new Chunk("3." + (i+1) + ".Nome: " , fonte3));
                            p18.add(new Chunk(nomeAlternativa, fonte4));
                            p18.setIndentationLeft(12);
                            doc.add(new Paragraph(p18));

                            String descricaoAlternativa = alternativasList.get(i).getData("Alternativa.descricao");
                            Paragraph p19 = new Paragraph();
                            p19.add(new Chunk("Descrição: " , fonte3));
                            p19.add(new Chunk(descricaoAlternativa, fonte4));
                            p19.setIndentationLeft(29);
                            doc.add(new Paragraph(p19));

                            String custoAlternativa = alternativasList.get(i).getData("Alternativa.estimativaCusto");
                            Paragraph p20 = new Paragraph();
                            p20.add(new Chunk("Estimativa de Custo: " , fonte3));
                            p20.add(new Chunk(custoAlternativa, fonte4));
                            p20.setIndentationLeft(29);
                            doc.add(new Paragraph(p20));

                            String tempoAlternativa = alternativasList.get(i).getData("Alternativa.estimativaTempo");
                            Paragraph p21 = new Paragraph();
                            p21.add(new Chunk("Estimativa de Tempo: " , fonte3));
                            p21.add(new Chunk(tempoAlternativa, fonte4));
                            p21.setIndentationLeft(29);
                            doc.add(new Paragraph(p21));
                            
                            String metodosAlternativa = alternativasList.get(i).getData("Alternativa.metodos");
                            Paragraph p22 = new Paragraph();
                            p22.add(new Chunk("Métodos de Avaliação: " , fonte3));
                            p22.add(new Chunk(metodosAlternativa, fonte4));
                            p22.setIndentationLeft(29);
                            p22.setSpacingAfter(4);
                            doc.add(new Paragraph(p22));

//                            Paragraph p22 = new Paragraph(" ");
//                            doc.add(p22);
                        }
                    
                    //CRITÉRIOS DE AVALIAÇÃO
                    
                        PdfPTable table3 = new PdfPTable(1);
                        table3.setWidthPercentage(100);
                        table3.setHorizontalAlignment(Element.ALIGN_LEFT);
                        PdfPCell cell3;
                        cell3 = new PdfPCell();
                        cell3.setBorder(PdfPCell.BOX);
                        cell3.addElement(new Paragraph("4.CRITÉRIOS DE AVALIAÇÃO", fonte3));
                        table3.addCell(cell3);
                        table3.setSpacingBefore(6);
                        doc.add(table3);

                        //for para listar os critérios do problema
                        int idProblem = Integer.parseInt(KeepData.getData("Problema.id"));
                        List<Request> criteriosList = controllerCriterios.listCriteirosByProjeto(idProblem);
                        for (int i = 0; i < criteriosList.size(); i++) {
                            String nomeCriterio = criteriosList.get(i).getData("Criterio.nome");
                            Paragraph p24 = new Paragraph();
                            p24.add(new Chunk("4." + (i+1) + ".Nome: " , fonte3));
                            p24.add(new Chunk(nomeCriterio, fonte4));
                            p24.setIndentationLeft(12);
                            doc.add(new Paragraph(p24));

                            String pesoCriterio = criteriosList.get(i).getData("Criterio.peso");
                            Paragraph p25 = new Paragraph();
                            p25.add(new Chunk("Peso: " , fonte3));
                            p25.add(new Chunk(pesoCriterio, fonte4));
                            p25.setIndentationLeft(29);
                            doc.add(new Paragraph(p25));

                            String justificativaCriterio = criteriosList.get(i).getData("Criterio.justificativa");
                            Paragraph p26 = new Paragraph();
                            p26.add(new Chunk("Justificativa para o peso: " , fonte3));
                            p26.add(new Chunk(justificativaCriterio, fonte4));
                            p26.setIndentationLeft(29);
                            doc.add(new Paragraph(p26));

                            PdfPTable t3 = new PdfPTable(new float[]{0.40f, 0.40f, 0.20f});
                            //table.setWidthPercentage(30);
                            t3.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                            PdfPCell header40 = new PdfPCell(new Paragraph("Nota:", fonte3));
                            PdfPCell header50 = new PdfPCell(new Paragraph("Valor:", fonte3));
                            PdfPCell header60 = new PdfPCell(new Paragraph(" ", fonte3));
                            header40.setBorder(PdfPCell.NO_BORDER);
                            header50.setBorder(PdfPCell.NO_BORDER);
                            header60.setBorder(PdfPCell.NO_BORDER);
                            t3.addCell(header40);
                            t3.addCell(header50);
                            t3.addCell(header60);
                            
                            int idCriterios = Integer.parseInt(criteriosList.get(i).getData("Criterio.id"));
                            List<Request> notasList = controllerCriterios.listNotasByCriterio(idCriterios);
                            for (int j = 0; j < notasList.size(); j++) {
                                String notaCriterio = notasList.get(j).getData("Nota.nome");
                                String valorCriterio = notasList.get(j).getData("Nota.valor");
                                t3.addCell(new Phrase(notaCriterio, fonte4));
                                t3.addCell(new Phrase(valorCriterio, fonte4));  
                                t3.addCell(new Phrase(" "));
                            }
                            t3.setSpacingBefore(6);
                            t3.setSpacingAfter(6);
                            doc.add(t3);
                        }
                    
                    //AVALIAÇÃO
                    
                        PdfPTable table4 = new PdfPTable(1);
                        table4.setWidthPercentage(100);
                        table4.setHorizontalAlignment(Element.ALIGN_LEFT);
                        PdfPCell cell4;
                        cell4 = new PdfPCell();
                        cell4.setBorder(PdfPCell.BOX);
                        cell4.addElement(new Paragraph("5.AVALIAÇÃO", fonte3));
                        table4.addCell(cell4);
                        table4.setSpacingBefore(6);
                        doc.add(table4);

                        PdfPTable t = new PdfPTable(new float[] { 0.68f, 0.16f, 0.16f });
                        table.setWidthPercentage(80);
                        PdfPCell header = new PdfPCell(new Phrase("Alternativa", fonte3));
                        PdfPCell header2 = new PdfPCell(new Phrase("Satisfação", fonte3));
                        PdfPCell header3 = new PdfPCell(new Phrase("Ranking", fonte3));
                        t.addCell(header);
                        t.addCell(header2);
                        t.addCell(header3);
                        
                        //for para listar alternativas da avaliação
                        List<Request> avaliacaoList = controllerAvaliacao.getRequestListFromAlternativa(); 
                        for (int i = 0; i < avaliacaoList.size(); i++) {

                            String nomeAlternativas = avaliacaoList.get(i).getData("Avaliacao.alternativa.nome");
                            String satisfacaoAvaliar = avaliacaoList.get(i).getData("Avaliacao.satisfacao");
                            String rankingAvaliar = avaliacaoList.get(i).getData("Avaliacao.posicao");
                            t.addCell(new Phrase(nomeAlternativas, fonte4));
                            t.addCell(new Phrase(satisfacaoAvaliar, fonte4));
                            t.addCell(new Phrase(rankingAvaliar, fonte4));  
                        }
                        t.setSpacingBefore(6);
                        t.setSpacingAfter(6);
                        doc.add(t);
                    
                        request = controllerDecisao.findDecisao();
                        if (request.getData("Decisao.id")!=null) {
                    
                            String decisaoAvaliar = request.getData("Decisao.alternativa");
                            Paragraph p33 = new Paragraph();
                            p33.add(new Chunk("Decisão: " , fonte3));
                            p33.add(new Chunk(decisaoAvaliar, fonte4));
                            p33.setIndentationLeft(12);
                            doc.add(new Paragraph(p33)); 

                            String justificativaAvaliar = request.getData("Decisao.justificativa");
                            Paragraph p34 = new Paragraph();
                            p34.add(new Chunk("Justificativa: " , fonte3));
                            p34.add(new Chunk(justificativaAvaliar, fonte4));
                            p34.setIndentationLeft(12);
                            doc.add(new Paragraph(p34));
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