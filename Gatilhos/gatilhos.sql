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

/*Excluir todos os testes quando um grupo for excluido*/
CREATE OR REPLACE FUNCTION excluiTestesGrupos() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM grupo_teste CASCADE WHERE grupos_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluiTestesGrupos BEFORE
DELETE ON Grupo
FOR EACH ROW
EXECUTE PROCEDURE excluiTestesGrupos();

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

/*Excluir todos os testes de um grupo sempre que um teste for excluido*/
CREATE OR REPLACE FUNCTION excluiTestesRespondeTeste() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM grupo_teste CASCADE WHERE testesgrupo_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluiTestes BEFORE
DELETE ON Teste
FOR EACH ROW
EXECUTE PROCEDURE excluiTestes();

/*Excluir as informações da tabela responde teste sempre que um grupo for excluido*/
CREATE OR REPLACE FUNCTION excluiRespondeTeste() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM respondeExercicio CASCADE WHERE grupo_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerRespondeTeste BEFORE
DELETE ON Grupo
FOR EACH ROW
EXECUTE PROCEDURE excluiRespondeTeste();

/*Excluir todos os membros de uma turma sempre que a mesma for excluida*/
CREATE OR REPLACE FUNCTION excluiMembroTurma() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM turma_aluno CASCADE WHERE turmas_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirMembroGrupo BEFORE
DELETE ON Turma
FOR EACH ROW
EXECUTE PROCEDURE excluiMembroTurma();

/*Excluir frequencia de uma turma sempre que a mesma for excluida*/
CREATE OR REPLACE FUNCTION excluiPresencaTurma() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM presenca CASCADE WHERE turma_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirPresencaTurma BEFORE
DELETE ON Turma
FOR EACH ROW
EXECUTE PROCEDURE excluiPresencaTurma();

/*Excluir nota de uma turma sempre que a mesma for excluida*/
CREATE OR REPLACE FUNCTION excluiNotaTurma() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM nota CASCADE WHERE turma_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirNotaTurma BEFORE
DELETE ON Turma
FOR EACH ROW
EXECUTE PROCEDURE excluiNotaTurma();


/*Excluir horario de uma turma sempre que a mesma for excluida*/
CREATE OR REPLACE FUNCTION excluiHorarioTurma() RETURNS TRIGGER 
AS'
  BEGIN
     DELETE FROM horario CASCADE WHERE turma_codigo = OLD.codigo;
     RETURN OLD;
  END '
LANGUAGE plpgsql;

CREATE TRIGGER TriggerExcluirHorarioTurma BEFORE
DELETE ON Turma
FOR EACH ROW
EXECUTE PROCEDURE excluiHorarioTurma();