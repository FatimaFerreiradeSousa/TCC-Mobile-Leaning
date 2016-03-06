angular.module('starter')

.controller('loginCtrl', function($scope, $state, $stateParams, $http, fac){
	$scope.aluno = {
        nome:'',
        sobrenome:'',
        login:'aliu',
        senha:'12345',
        curso:'',
        descricao:'',
        dataParticipacao:'',
        foto:'',
        email:'',
        instituicao:'',
        foto:''
    }

	$scope.salvarAluno = function(){
		fac.salvar($scope.aluno);
	}

	$scope.loginAl = function(){
		fac.loginAluno($scope.aluno);
	}

})

.controller('alunoCtrl', function($scope, $state, $stateParams, $http, fac){

	$scope.login = $stateParams.login;

})

.controller('turmasCtrl', function($scope, $state, $stateParams, $http, fac){

	$scope.login = $stateParams.alunoLogin;

})