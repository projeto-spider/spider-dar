package controller;

import com.itextpdf.text.Chunk;
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
import java.util.Date;
import java.util.List;
import model.Historico;
import settings.Constant;
import settings.Facade;
import settings.KeepData;
import util.Request;
    
    public class ControllerRelatorio {
        
        Facade facade = Facade.getInstance();
        private Request request;
        private List<Request> requestList;
        private final ControllerProblema controllerProblema = new ControllerProblema();
        private final ControllerTarefas controllerTarefas = new ControllerTarefas();
        private final ControllerAlternativa controllerAlternativa = new ControllerAlternativa();
        
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
        
        public void gerarRelatorio() throws IOException, DocumentException {
            
            Document doc = null;
            OutputStream os = null;
            Request request = new Request();

	        try {
                    
                    //fontes
                    Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
                    Font fonte2 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
                    Font fonte3 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
                    Font fonte4 = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
                    
	            //cria o documento tamanho A4, margens de 2,54cm
                    doc = new Document(PageSize.A4, 72, 72, 72, 72);

	            //cria a stream de saída
	            os = new FileOutputStream("Relatório.pdf");
	       
	            //associa a stream de saída ao
	            PdfWriter.getInstance(doc, os);

	            //abre o documento
	            doc.open();	 

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
                    
                    String nomeProblema = KeepData.getData("Problema.nome");
                    Paragraph p4 = new Paragraph("1. Problema: " + nomeProblema, fonte3);
                    doc.add(p4);
                    
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
                    
                    String contextoProblema = request.getData("Problema.planejamento");
                    Paragraph p7 = new Paragraph();
                    p7.add(new Chunk("Contexto/Cenário: " , fonte3));
                    p7.add(new Chunk(contextoProblema, fonte4));
                    p7.setIndentationLeft(12);
                    doc.add(new Paragraph(p7));
                  
                    Paragraph p8 = new Paragraph("Envolvidos:", fonte3);
                    p8.setIndentationLeft(12);
                    doc.add(p8);
                    
                    //for para listar todos os envolvidos no problema
                    
                    
                    Paragraph p9 = new Paragraph(" ");
                    doc.add(p9);
                    
                    Paragraph p10 = new Paragraph("2. Tarefas: ", fonte3);
                    doc.add(p10);
                    
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
                        
                        String statusTarefa = tarefasList.get(i).getData("Tarefa.feito");
                        Paragraph p15 = new Paragraph();
                        p15.add(new Chunk("Status: " , fonte3));
                        p15.add(new Chunk(statusTarefa, fonte4));
                        p15.setIndentationLeft(12);
                        doc.add(new Paragraph(p15));  
                    
                        Paragraph p16 = new Paragraph(" ");
                        doc.add(p16);
                    } 
                    
                    Paragraph p17 = new Paragraph("3. Alternativas de Solução: ", fonte3);
                    doc.add(p17);
                    
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
                    
                    Paragraph p23 = new Paragraph("4. Critérios de Avaliação: ", fonte3);
                    doc.add(p23);
                    
                    //for para listar os critérios do problema
	 
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