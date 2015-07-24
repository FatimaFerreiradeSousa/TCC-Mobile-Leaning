/*Excluir todos os topicos quando um grupo for excluido*/
CREATE OR REPLACE FUNCTION excluiTopicos() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM Topico CASCADE WHERE grupo_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirTopico BEFORE
DELETE ON Grupo
FOR EACH ROW
EXECUTE PROCEDURE excluiTopicos();

/*Excluir todos os comentarios quando um topico for excluido*/
CREATE OR REPLACE FUNCTION excluiComentarios() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM Comentario CASCADE WHERE topico_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirComentario BEFORE
DELETE ON Topico
FOR EACH ROW
EXECUTE PROCEDURE excluiComentarios();

/*Excluir todos dados da tabela responde teste quando um grupo for excluido*/