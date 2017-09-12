# SQL inicial Spider-DAR
# Necessita ser executado toda vez que o banco for criado
        
use spiderdar;
    
SET FOREIGN_KEY_CHECKS=0;
    
TRUNCATE acessar;
TRUNCATE alternativa;
TRUNCATE avaliar;
TRUNCATE configuracoes;
TRUNCATE conter;
TRUNCATE criterio;
TRUNCATE decisao;
TRUNCATE funcionalidades;
TRUNCATE guia;
TRUNCATE historico;
TRUNCATE itemguia;
TRUNCATE keyword;
TRUNCATE nota;
TRUNCATE organizacao;
TRUNCATE perfil;
TRUNCATE possuir;
TRUNCATE problema;
TRUNCATE tarefa;
TRUNCATE usuario;
    
# inserção dos funcionalidades necessários para a execução da ferramenta
insert
into funcionalidades
values(1, 'Gerenciar - Problemas'),
      (2, 'Gerenciar - Usuários'),
      (3, 'Gerenciar - Permissões de Perfil'),
      (4, 'Problema - Motivação e Objetivos'),
      (5, 'Problema - Tarefas'),
      (6, 'Problema - Alternativas de Solução'),
      (7, 'Problema - Critérios de Avaliação'),
      (8, 'Problema - Avaliação'),
      (9, 'Problema - Histórico'),
      (10, 'Problema - Relatório');
    
INSERT INTO organizacao(`id`,`nome`,`descricao`,`created`,`modified`)
VALUES(1,'Default-Org','Default-Org',now(),now());
    
    
INSERT INTO problema(`id`,`idOrganizacao`,`nome`,`proposito`,`planejamento`,`contexto`,`status`,`created`,`modified`)
VALUES
(1,1,'Default-problema','Default-problema','Default-problema','Default-problema',1,now(),now());
    
insert
into usuario 
values (1, 'adm', 'adm', 'adm@gmail.com', 'bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a', now(), now());
    
insert
into perfil 
values (1, 1, 'Administrador-spiderDAR', 'Administrador-spiderDAR', 'Administrador', now(), now());
    
insert
into acessar 
values (1, 1, 1, 1);
  
insert
into configuracoes 
values (1, 'projetospiderdar@gmail.com', '5p1d3rd4r', 'smtp.gmail.com', '465', 'SSL', now(), now());
    
SET FOREIGN_KEY_CHECKS=1;