angular.module('starter')

.controller('alunoCtrl', function($scope, $state, $stateParams, $http, fac){

	$scope.salvarAluno = function(){
		fac.salvar();
	}

})