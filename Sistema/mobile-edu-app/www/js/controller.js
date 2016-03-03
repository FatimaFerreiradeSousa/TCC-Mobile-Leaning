var aluno = {
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

angular.module('starter')

.controller("app-controller", function($scope, $state, $stateParams, $http) {

	$scope.cadastrarAluno = function(aluno){
		
		var url = "http://192.168.2.10:8080/servidor/Cadastro";
        
        $http.post(url, aluno).success(function(status) {

            alert("Salvo Com Sucesso!");
            delete $scope.aluno;
            $state.go("login");

        });
    };

    $scope.loginAluno = function(al){
        var url = "http://192.168.2.10:8080/servidor/Login";
        
        $http.post(url, al)

        .then(function(response) {
            $scope.aluno = response.data;
            alert($scope.aluno.login);
            $state.go("home"); 
        });

        /*
        $http.get(url).then(function(response){
        	$scope.aluno = response.data;
        	$state.go("home");   
        })*/

        ///$state.go("home");
    }

/*
    var url = "http://192.168.2.10:8080/servidor/Turmas";
        
    $http.get(url).then(function(response){
        	$scope.turmas = response.data;
    })	

    var url = "http://192.168.2.10:8080/servidor/Login";

    $http.get(url).then(function(response){
        $scope.aluno = response.data;
    })*/

});