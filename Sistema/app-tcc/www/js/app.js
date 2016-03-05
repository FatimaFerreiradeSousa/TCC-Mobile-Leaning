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
  
    $stateProvider.state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'alunoCtrl'
    })

    .state('cadastro', {
        url: '/cadastro',
        templateUrl: 'templates/cadastro.html',
        controller: 'alunoCtrl'
    })

    .state('senha', {
        url: '/senha',
        templateUrl: 'templates/senha.html',
        controller: 'alunoCtrl'
    })

    .state('home', {
        url: '/home',
        templateUrl: 'templates/home.html',
        controller: 'horrarioCtrl'
    })

    .state('turmas', {
        url: '/turmas',
        templateUrl: 'templates/turmas.html',
        controller: 'turmasCtrl'
    })

    .state('turmaHome', {
        url: '/turmaHome',
        templateUrl: 'templates/turmaHome.html',
        controller: 'turmaSelectCtrl'
    })

    .state('alunosTurma', {
        url: '/alunosTurma',
        templateUrl: 'templates/alunosTurma.html',
        controller: 'turmaSelectCtrl'
    })

    .state('notasTurma', {
        url: '/notasTurma',
        templateUrl: 'templates/notasTurma.html',
        controller: 'turmaSelectCtrl'
    })

    .state('horarioTurma', {
        url: '/horarioTurma',
        templateUrl: 'templates/horarioTurma.html',
        controller: 'turmaSelectCtrl'
    })

    .state('verNota', {
        url: '/verNota',
        templateUrl: 'templates/verNota.html',
        controller: 'turmaSelectCtrl'
    })

   $urlRouterProvider.otherwise('/login');

});
