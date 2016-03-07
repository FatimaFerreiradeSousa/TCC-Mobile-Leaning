angular.module('starter').factory('fac', function($state, $http) {

    var obj =  {
      
      salvar: function(aluno) {
        var url = "http://192.168.2.9:8080/App-Servidor/Cadastro";
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

        var url = "http://192.168.2.9:8080/App-Servidor/Login"
        $http.post(url, aluno).then(function(response){
            alunoObj = response.data;
            $state.go("home", {login:alunoObj.login})
        });
      }
    }

    return obj;

})










