package com.br.util;

import com.br.entidades.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;
import com.br.util.FotosServices;

/**
 *
 * @author Diego
 */
public class UtilTest {

    private static JSONObject jSONObject;
    private static JSONParser parser;

    public UtilTest() {
    }

    public static JSONObject getJSON(String json) {

        jSONObject = new JSONObject(json);
        return jSONObject;
    }

    public static String streamToString(InputStream is) throws IOException {
        byte[] bytes = new byte[1024];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int lidos;
        while ((lidos = is.read(bytes)) > 0) {
            baos.write(bytes, 0, lidos);
        }
        return new String(baos.toByteArray());
    }

    public static JSONObject getJSONTurma(Turma turma) throws IOException {

        jSONObject = new JSONObject();
        jSONObject.put("nome", turma.getNome());
        jSONObject.put("categoria", turma.getCategoria());
        jSONObject.put("codigo", turma.getCodigo());
        jSONObject.put("dataInicio", turma.getDataInicio());
        jSONObject.put("dataTerminio", turma.getDataTerminio());
        jSONObject.put("professor", turma.getProfessor().getNome());
        jSONObject.put("descricao", turma.getDescricao());

        return jSONObject;
    }

    public static JSONObject getJSONObject(Aluno json) throws IOException {

        jSONObject = new JSONObject();
        jSONObject.put("nome", json.getNome());
        jSONObject.put("sobrenome", json.getSobrenome());
        jSONObject.put("login", json.getLogin());
        jSONObject.put("senha", json.getSenha());
        jSONObject.put("instituicao", json.getInstituicao());
        jSONObject.put("descricao", json.getDescricao());
        jSONObject.put("foto", FotosServices.converteArquivoEmStringBase64(json.getFoto()));
        jSONObject.put("email", json.getEmail());
        jSONObject.put("curso", json.getCurso());

        return jSONObject;
    }

    public static JSONObject getJSONAluno(Aluno json, int presenca, int faltas) throws IOException {

        jSONObject = new JSONObject();
        jSONObject.put("sobrenome", json.getSobrenome());
        jSONObject.put("nome", json.getNome());
        jSONObject.put("login", json.getLogin());
        jSONObject.put("senha", json.getSenha());
        jSONObject.put("instituicao", json.getInstituicao());
        jSONObject.put("descricao", json.getDescricao());
        jSONObject.put("foto", FotosServices.converteArquivoEmStringBase64(json.getFoto()));
        jSONObject.put("email", json.getEmail());
        jSONObject.put("curso", json.getCurso());
        jSONObject.put("presenca", presenca);
        jSONObject.put("faltas", faltas);

        return jSONObject;
    }

    public static JSONObject getJSONNota(Nota nota) throws IOException {

        jSONObject = new JSONObject();
        jSONObject.put("titulo", nota.getTitulo());
        jSONObject.put("data", FormatData.parseDateString(nota.getDataNota()));
        jSONObject.put("introducao", nota.getIntroducao());
        jSONObject.put("desenvolvimento", nota.getDesenvolvimento());
        jSONObject.put("professor", nota.getProfessor());

        return jSONObject;
    }

    public static JSONObject getJSONGrupo(Grupo grupo, int membros) throws IOException {

        jSONObject = new JSONObject();
        jSONObject.put("codigo", grupo.getCodigo());
        jSONObject.put("data", FormatData.parseDateString(grupo.getDataCriacao()));
        jSONObject.put("descricao", grupo.getDescricao());
        jSONObject.put("nome", grupo.getNome());
        jSONObject.put("professorNome", grupo.getProfessorGrupos().getNome());
        jSONObject.put("professorSobremome", grupo.getProfessorGrupos().getSobrenome());
        jSONObject.put("membros", membros);

        return jSONObject;
    }

    public static JSONObject getJSONAlunoInfo(ParticipaGrupo participaGrupo, int testes, int publicacoes, int testesGrupo)
            throws IOException {

        jSONObject = new JSONObject();
        jSONObject.put("codigo", participaGrupo.getId());
        jSONObject.put("data", FormatData.parseDateString(participaGrupo.getDataParticipacao()));
        jSONObject.put("pontuacao", participaGrupo.getPontuacao());
        jSONObject.put("testes", testes);
        jSONObject.put("publicacoes", publicacoes);
        jSONObject.put("testesGrupo", testesGrupo);
        jSONObject.put("alunoNome", participaGrupo.getAluno().getNome());
        jSONObject.put("alunoSobrenome", participaGrupo.getAluno().getSobrenome());
        jSONObject.put("foto", FotosServices.converteArquivoEmStringBase64(participaGrupo.getAluno().getFoto()));

        return jSONObject;
    }
}
