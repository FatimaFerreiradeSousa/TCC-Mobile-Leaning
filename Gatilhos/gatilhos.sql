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

/*Excluir todos os membros de um grupo sempre que o mesmo for excluido*/
CREATE OR REPLACE FUNCTION excluiMembroGrupo() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM ParticipaGrupo CASCADE WHERE grupo_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirMembroGrupo BEFORE
DELETE ON Grupo
FOR EACH ROW
EXECUTE PROCEDURE excluiMembroGrupo();

/*Excluir as informações da tabela responde teste sempre que um grupo for excluido*/
CREATE OR REPLACE FUNCTION excluiRespondeTeste() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM respondeExercicio CASCADE WHERE codteste = OLD.codigoteste;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerRespondeTeste BEFORE
DELETE ON Topico
FOR EACH ROW
EXECUTE PROCEDURE excluiRespondeTeste();

/*Atualizar Aluno*/

CREATE OR REPLACE FUNCTION respondeAlunoRespTeste() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM aluno_respondeExercicio CASCADE WHERE respondeexercicio_id = OLD.id;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerAlunoRespTeste BEFORE
DELETE ON respondeExercicio
FOR EACH ROW
EXECUTE PROCEDURE respondeAlunoRespTeste();
