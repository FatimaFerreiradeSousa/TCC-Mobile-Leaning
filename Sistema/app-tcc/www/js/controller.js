angular.module('starter').controller('alunoCtrl', function($scope, $state, $stateParams, $http){
	
	$scope.aluno = {
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

    function buscarAluno(){
        var url = "http://192.168.2.5:8080/servidor/Login"
        $http.get(url).then(function(response){
            $scope.aluno = response.data;
        })
    }

    $scope.aluno = buscarAluno();

    $scope.login = function(){
        //$scope.aluno.login = aluno;
        $state.go("home", {
        	reload:true
        });
    };


    $scope.cadastrarAluno = function(){
        
        var url = "http://192.168.2.5:8080/servidor/Cadastro";
        
        $http.post(url, $scope.aluno).success(function(status) {

            alert("Salvo Com Sucesso!");
            delete $scope.aluno;
            $state.go("login");

        });
    };

    var url = "http://192.168.2.5:8080/servidor/Turmas";
        
    $http.post(url, $scope.aluno).then(function(response) {
        $scope.turmas = response.data;
    });
    
})