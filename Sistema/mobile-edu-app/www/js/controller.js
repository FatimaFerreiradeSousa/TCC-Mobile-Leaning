angular.module('starter')

.controller('loginCtrl', function($scope, $state, $stateParams, $http, fac){
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

	$scope.salvarAluno = function(){
		fac.salvar($scope.aluno);
	}

	$scope.loginAl = function(){
		fac.loginAluno($scope.aluno);
	}

})

.controller('alunoCtrl', function($scope, $state, $stateParams, $http, fac){

	$scope.alunoSessao = {
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

	var caminho = "http://192.168.2.8:8080/App-Servidor/Login?loginAl="
    var url = caminho.concat($stateParams.login);

    $http.get(url).then(function(response){
        $scope.alunoSessao = response.data;
    });

    /*Horario do dia*/
    var aux = "http://192.168.2.8:8080/App-Servidor/Horarios?loginAl=";
    var temp = aux.concat($stateParams.login);

    $http.get(temp).then(function(response) {
        $scope.turmas = response.data;
    });
})

.controller('turmasCtrl', function($scope, $state, $stateParams, $http){

	$scope.login = $stateParams.alunoLogin;

	var caminho = "http://192.168.2.8:8080/App-Servidor/Turmas?loginAl=";
    var url = caminho.concat($stateParams.alunoLogin);

    $http.get(url).then(function(response) {
        $scope.turmasAluno = response.data;
    });

    $scope.codigoTurma = $stateParams.turmaCod;
})

.controller('turmaHomeCtrl', function($scope, $state, $stateParams, $http){

    $scope.codigoTurma = $stateParams.turmaCodigo;

    var caminho = "http://192.168.2.8:8080/App-Servidor/TurmaSelect?codigo=";
    var url = caminho.concat($stateParams.turmaCodigo);

    $http.get(url).then(function(response) {
        $scope.turma = response.data;
    });

    var caminho1 = "http://192.168.2.8:8080/App-Servidor/HorarioDia?codigo=";
    var url1 = caminho1.concat($stateParams.turmaCodigo)
        
    $http.get(url1).then(function(response) {
        $scope.horarioDia = response.data;
    });

    /*
    var caminho2 = "http://192.168.2.9:8080/App-Servidor/HorariosTurma?codigo=";
    var url2 = caminho1.concat($stateParams.turmaCodigo)
        
    $http.get(url2).then(function(response) {
        $scope.horarios = response.data;
    });*/

})

.controller('turmaHorarioCtrl', function($scope, $state, $stateParams, $http){

	$scope.cod = $stateParams.codTurma;

	var caminho = "http://192.168.2.8:8080/App-Servidor/HorariosTurma?codigo=";
	var segunda = caminho.concat($stateParams.codTurma);
    var url = segunda.concat("&dia=Segunda-Feira")
        
    $http.get(url).then(function(response) {
        $scope.horariosSegunda = response.data;
    })

    var caminho1 = "http://192.168.2.8:8080/App-Servidor/HorariosTurma?codigo=";
	var terca = caminho1.concat($stateParams.codTurma);
    var url1 = terca.concat("&dia=Terça-Feira")
        
    $http.get(url1).then(function(response) {
        $scope.horariosTerca = response.data;
    })

    var caminho2 = "http://192.168.2.8:8080/App-Servidor/HorariosTurma?codigo=";
	var quarta = caminho2.concat($stateParams.codTurma);
    var url2 = terca.concat("&dia=Quarta-Feira")
        
    $http.get(url2).then(function(response) {
        $scope.horariosQuarta = response.data;
    })

    var caminho3 = "http://192.168.2.8:8080/App-Servidor/HorariosTurma?codigo=";
	var quinta = caminho3.concat($stateParams.codTurma);
    var url3 = terca.concat("&dia=Quinta-Feira")
        
    $http.get(url3).then(function(response) {
        $scope.horariosQuinta = response.data;
    })

    var caminho4 = "http://192.168.2.8:8080/App-Servidor/HorariosTurma?codigo=";
	var sexta = caminho4.concat($stateParams.codTurma);
    var url4 = terca.concat("&dia=Sexta-Feira")
        
    $http.get(url4).then(function(response) {
        $scope.horariosSexta = response.data;
    })

})

.controller('turmaAlunosCtrl', function($scope, $state, $stateParams, $http){

    $scope.turmaAluno = $stateParams.codigoTurma;

	var caminho = "http://192.168.2.8:8080/App-Servidor/AlunosTurma?codigo=";
	var url = caminho.concat($stateParams.codigoTurma)
        
    $http.get(url).then(function(response) {
        $scope.alunosTurma = response.data;
    })    

})

.controller('alunoDetalheCtrl', function($scope, $state, $stateParams, $http){

    $scope.alunoId = $stateParams.alunoId;

	/*var caminho = "http://192.168.2.8:8080/App-Servidor/AlunosTurma?codigo=";
	var url = caminho.concat($stateParams.codigoTurma)
        
    $http.get(url).then(function(response) {
        $scope.alunosTurma = response.data;
    })*/    

})

.controller('turmaNotasCtrl', function($scope, $state, $stateParams, $http){

    $scope.notasTurmaId = $stateParams.turmaCod;

	var caminho = "http://192.168.2.8:8080/App-Servidor/NotasTurma?codigo=";
	var url = caminho.concat($stateParams.turmaCod)
        
    $http.get(url).then(function(response) {
        $scope.notas = response.data;
    })    

})

.controller('verNotasCtrl', function($scope, $state, $stateParams, $http){

    $scope.notaCod = $stateParams.notaId;

    /*
	var caminho = "http://192.168.2.8:8080/App-Servidor/NotasTurma?codigo=";
	var url = caminho.concat($stateParams.turmaCod)
        
    $http.get(url).then(function(response) {
        $scope.notas = response.data;
    })*/    

})


























