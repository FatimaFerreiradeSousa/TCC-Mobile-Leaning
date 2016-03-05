angular.module('starter')

.controller('alunoCtrl', function($scope, $state, $stateParams, $http){
	
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
        var url = "http://192.168.2.9:8080/Server/Login"
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
        
        var url = "http://192.168.2.9:8080/Server/Cadastro";
        
        $http.post(url, $scope.aluno).success(function(status) {

            alert("Salvo Com Sucesso!");
            delete $scope.aluno;
            $state.go("login");

        });
    };
    
})

.controller('horrarioCtrl', function($scope, $state, $stateParams, $http){

    var url = "http://192.168.2.9:8080/Server/Horarios";
        
    $http.get(url).then(function(response) {
        $scope.turmas = response.data;
    });
})

.controller('turmasCtrl', function($scope, $state, $stateParams, $http){

    var url = "http://192.168.2.9:8080/Server/Turmas";
        
    $http.get(url).then(function(response) {
        $scope.turmasAluno = response.data;
    });
})

.controller('turmaSelectCtrl', function($scope, $state, $stateParams, $http){

    var url = "http://192.168.2.9:8080/Server/TurmaSelect";
        
    $http.get(url).then(function(response) {
        $scope.turma = response.data;
    });

    var url = "http://192.168.2.9:8080/Server/HorarioDia";
        
    $http.get(url).then(function(response) {
        $scope.horariosTurma = response.data;
    });

    var url = "http://192.168.2.9:8080/Server/AlunosTurma";
        
    $http.get(url).then(function(response) {
        $scope.alunos = response.data;
    });

    var url = "http://192.168.2.9:8080/Server/NotasTurma";
        
    $http.get(url).then(function(response) {
        $scope.notas = response.data;
    });
})



















