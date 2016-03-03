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
        controller: 'app-controller'
    })

    .state('cadastro', {
        url: '/cadastro',
        templateUrl: 'templates/cadastro.html',
        controller: 'app-controller'
    })

    .state('senha', {
        url: '/senha',
        templateUrl: 'templates/senha.html',
        controller: 'app-controller'
    })

    .state('home', {
        url: '/home',
        templateUrl: 'templates/home.html',
        controller: 'app-controller'
    })

    .state('turmas', {
        url: '/turmas',
        templateUrl: 'templates/turmas.html',
        controller: 'app-controller'
    })

    .state('grupos', {
        url: '/grupos',
        templateUrl: 'templates/grupos.html',
        controller: 'app-controller'
    })

    .state('configuracoes', {
        url: '/configuracoes',
        templateUrl: 'templates/configuracoes.html',
        controller: 'app-controller'
    })
 
   $urlRouterProvider.otherwise('/login');

   
    $httpProvider.defaults.useXDomain = true;
    $httpProvider.defaults.withCredentials = true;
    $httpProvider.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});
