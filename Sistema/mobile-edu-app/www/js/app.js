angular.module('starter', ['ionic'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    if(window.cordova && window.cordova.plugins.Keyboard) {

      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);

      cordova.plugins.Keyboard.disableScroll(true);
    }
    if(window.StatusBar) {
      StatusBar.styleDefault();
    }
  });
})

.config(function($stateProvider, $urlRouterProvider, $httpProvider) {
  
  $stateProvider
    
    .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'loginCtrl'
    })

    .state('home', {
        url: '/home/:login',
        templateUrl: 'templates/home.html',
        controller: 'alunoCtrl'
    })

    .state('cadastro', {
        url: '/cadastro',
        templateUrl: 'templates/cadastro.html',
        controller: 'alunoCtrl'
    })

    .state('turmas', {
        url: '/turmas/:alunoLogin',
        templateUrl: 'templates/turmas.html',
        controller: 'turmasCtrl'
    })

    .state('pageTurma', {
        url: '/pageTurma/:turmaCodigo',
        templateUrl: 'templates/pageTurma.html',
        controller: 'turmaHomeCtrl'
    })

    .state('horariosTurma', {
        url: '/horariosTurma/:codTurma',
        templateUrl: 'templates/horariosTurma.html',
        controller: 'turmaHorarioCtrl'
    })

    .state('alunosTurmas', {
        url: '/alunosTurmas/:codigoTurma',
        templateUrl: 'templates/alunosTurmas.html',
        controller: 'turmaAlunosCtrl'
    })

    .state('alunoDetalhe', {
        url: '/alunoDetalhe/:alunoId',
        templateUrl: 'templates/alunoDetalhe.html',
        controller: 'alunoDetalheCtrl'
    })

    .state('notasTurma', {
        url: '/notasTurma/:turmaCod',
        templateUrl: 'templates/notasTurma.html',
        controller: 'turmaNotasCtrl'
    })

    .state('verNota', {
        url: '/verNota/:notaId',
        templateUrl: 'templates/verNota.html',
        controller: 'verNotasCtrl'
    })

    .state('grupos', {
        url: '/grupos/:loginAluno',
        templateUrl: 'templates/grupos.html',
        controller: 'gruposCtrl'
    })

    .state('homeGrupo', {
        url: '/homeGrupo/:codGrupo',
        templateUrl: 'templates/homeGrupo.html',
        controller: 'homeGrupoCtrl'
    })

    .state('alunosGrupo', {
        url: '/alunosGrupo/:codGrupo',
        templateUrl: 'templates/alunosGrupo.html',
        controller: 'alunosGrupoCtrl'
    })

    .state('arquivos', {
        url: '/arquivos/:codGrupo',
        templateUrl: 'templates/arquivos.html',
        controller: 'arquivosGrupoCtrl'
    })

    .state('testesGrupo', {
        url: '/testesGrupo/:codGrupo',
        templateUrl: 'templates/testesGrupo.html',
        controller: 'testesGrupoGrupoCtrl'
    })

    .state('testesResultados', {
        url: '/testesResultados/:codGrupo',
        templateUrl: 'templates/testesResultados.html',
        controller: 'testesGrupoGrupoCtrl'
    })

    .state('infoGrupo', {
        url: '/infoGrupo/:codGrupo',
        templateUrl: 'templates/infoGrupo.html',
        controller: 'infoGrupoCtrl'
    })

    $urlRouterProvider.otherwise('/login');

})
