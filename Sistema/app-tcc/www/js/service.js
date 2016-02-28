angular.module('starter.serviceFAC', [])

.factory('fac', function($http) {
   
    var obj =  {
      salvarAluno: function(aluno) {
        var url = "http://192.168.2.10:8080/servidor/Cadastro";

        $http.post(url, aluno).success(function(status) {
            alert("Fabrica: " +aluno.nome);
        });
      },

    }

    return obj;

})
