angular.module('starter').factory('fac', function($state, $http) {

    var obj =  {
      
      salvar: function(aluno) {
        var url = "http://192.168.2.5:8080/App-Servidor/Cadastro";
        var status = "";

        $http.post(url, aluno).then(function(response) {
            status = response.data;
            
            if(status.localeCompare('true') == 0){
                alert("Cadastro realizado com sucesso!");
                $state.go("login");  
            }else{
                alert("Login/Email indisponivel!");
                $state.go("cadastro");
            }
            
        });
      },

      loginAluno: function(aluno){

        var alunoObj = {
          nome:'',
          sobrenome:'',
          login:'',
          senha:'',
          curso:'',
          descricao:'',
          dataParticipacao:'',
          foto:'',
          email:'',
          instituicao:'',
          foto:''
        }

        var url = "http://192.168.2.5:8080/App-Servidor/Login"
        $http.post(url, aluno).then(function(response){
            alunoObj = response.data;
            $state.go("home", {login:alunoObj.login})
        });
      },

      salvarTopico: function(topico){
        var url = "http://192.168.2.5:8080/App-Servidor/Topicos"
        $http.post(url, topico).then(function(response){
            $state.go("homeGrupo")
        }); 
      },

      removerTopicos: function(topico){
        var url = "http://192.168.2.5:8080/App-Servidor/RemoverTopico"
        $http.post(url, topico).then(function(response){
            alert(response.data);
            $state.go("homeGrupo")
        });
      },

      alterarTopicos: function(topico){
        alert("Topico Alterar: " +topico.codigo);
        var url = "http://192.168.2.5:8080/App-Servidor/AtualizarTopico"
        $http.post(url, topico).then(function(response){
            alert(response.data);
            $state.go("alterarTopico")
        });
        
      },

      salvarComentario: function(comentario){
        var url = "http://192.168.2.5:8080/App-Servidor/Comentarios"
        $http.post(url, comentario).then(function(response){
            $state.go("comentarios")
        });
      },

      removerComentario: function(comentario){
        var url = "http://192.168.2.5:8080/App-Servidor/RemoverComentario"
        $http.post(url, comentario).then(function(response){
            alert(response.data);
            $state.go("comentarios")
        });
      },

      alterarComentarios: function(comentario){
        var url = "http://192.168.2.5:8080/App-Servidor/AlterarComentario"
        $http.post(url, comentario).then(function(response){
            alert(response.data);
            $state.go("comentarios")
        });
      }      
    }

    return obj;

})










