
angular.module('starter').controller("app-controller", function($scope, $state, $stateParams, $http, fac) {
    
    $scope.aluno = {
        nome:'',
        sobrenome:'',
        login:'Fatinha',
        senha:'',
        curso:'',
        descricao:'',
        dataParticipacao:'',
        foto:'',
        email:'',
        instituicao:'',
        foto:''
    }

    $scope.cadastrarAluno = function(aluno){
		
		var url = "http://192.168.2.5:8080/servidor/Cadastro";
        
        $http.post(url, aluno).success(function(status) {

            alert("Salvo Com Sucesso!");
            delete $scope.aluno;
            $state.go("login");

        });
    };

    $scope.loginAluno = function(al){
       fac.salvar(al);

        $scope.aluno = angular.copy(al);
       //alert($scope.aluno.login);
       // $scope.aluno = al;
        /*var url = "http://192.168.2.5:8080/servidor/Login";
        
        $http.post(url, al)

        .then(function(response) {
            $scope.aluno = response.data;
            $scope.$apply();
            alert($scope.aluno.login);
            $state.go("home"); 
        });
        
        /*
        $http.get(url).then(function(response){
        	$scope.aluno = response.data;
        	$state.go("home");   
        })*/

        $state.go("home");
    };

});