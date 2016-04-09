angular.module('starter', ['ionic', 'ngCordova', 'ionic-material'])

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
    
    .state('app', {
        url: '/app',
        abstract: true,
        templateUrl: 'templates/menu.html',
        controller: 'alunoCtrl'
    })

    .state('app.home', {
        url: '/home/:login',
        views: {
            'menuContent': {
                templateUrl: 'templates/home.html',
                controller: 'alunoCtrl'
            }
        }
    })

    .state('app.turmas', {
        url: '/turmas/:alunoLogin',
        views: {
            'menuContent': {
                templateUrl: 'templates/turmas.html',
                controller: 'turmasCtrl'
            }
        }
    })

    .state('app.pageTurma', {
        url: '/pageTurma/:turmaCodigo/:loginAluno',
        views: {
            'menuContent': {
                templateUrl: 'templates/pageTurma.html',
                controller: 'turmaHomeCtrl'
            }
        }
    })

    .state('app.horariosTurma', {
        url: '/horariosTurma/:codTurma',
        views: {
            'menuContent': {
                templateUrl: 'templates/horariosTurma.html',
                controller: 'turmaHorarioCtrl'
            }
        }
    })

    .state('app.alunosTurmas', {
        url: '/alunosTurmas/:codigoTurma',
        views: {
            'menuContent': {
                templateUrl: 'templates/alunosTurmas.html',
                controller: 'turmaAlunosCtrl'
            }
        }
    })

    .state('app.alunoDetalhe', {
        url: '/alunoDetalhe/:alunoId/:turmaId',
        views: {
            'menuContent': {
                templateUrl: 'templates/alunoDetalhe.html',
                controller: 'alunoDetalheCtrl'
            }
        }
    })

    .state('app.presencasAluno', {
        url: '/presencasAluno/:turmaCodigo/:alunoLogin',
        views: {
            'menuContent': {
                templateUrl: 'templates/presencasAluno.html',
                controller: 'presencasAlunoCtrl'
            }
        }
    })

    .state('app.notasTurma', {
        url: '/notasTurma/:turmaCod',
        views: {
            'menuContent': {
                templateUrl: 'templates/notasTurma.html',
                controller: 'turmaNotasCtrl'
            }
        }
    })

    .state('app.verNota', {
        url: '/verNota/:notaId',
        views: {
            'menuContent': {
                templateUrl: 'templates/verNota.html',
                controller: 'verNotasCtrl'
            }
        }
    })

    .state('app.grupos', {
        url: '/grupos/:loginAluno',
        views: {
            'menuContent': {
                templateUrl: 'templates/grupos.html',
                controller: 'gruposCtrl'
            }
        }
    })

    .state('app.homeGrupo', {
        url: '/homeGrupo/:codGrupo/:loginAluno',
        views: {
            'menuContent': {
                templateUrl: 'templates/homeGrupo.html',
                controller: 'homeGrupoCtrl'
            }
        }
    })

    .state('app.alunosGrupo', {
        url: '/alunosGrupo/:codGrupo',
        views: {
            'menuContent': {
                templateUrl: 'templates/alunosGrupo.html',
                controller: 'alunosGrupoCtrl'
            }
        }
    })

    .state('testesGrupo', {
        url: '/testesGrupo/:codGrupo/:loginAluno',
        templateUrl: 'templates/testesGrupo.html',
        controller: 'testesGrupoGrupoCtrl'
    })

    .state('testesResultados', {
        url: '/testesResultados/:codGrupo',
        templateUrl: 'templates/testesResultados.html',
        controller: 'testesGrupoGrupoCtrl'
    })

    .state('resultados', {
        url: '/resultados/:codTeste',
        templateUrl: 'templates/resultados.html',
        controller: 'resultadosCtrl'
    })

    .state('app.alunoDetalheGrupo', {
        url: '/alunoDetalheGrupo/:loginAluno/:grupoId',
        views: {
            'menuContent': {
                templateUrl: 'templates/alunoDetalheGrupo.html',
                controller: 'alunoDetalheGrupoCtrl'
            }
        }
    })

    .state('app.comentarios', {
        url: '/comentarios/:codigoTopico/:loginAluno',
        views: {
            'menuContent': {
                templateUrl: 'templates/comentarios.html',
                controller: 'comentariosCtrl'
            }
        }
    })

    .state('app.alterarComentario', {
        url: '/alterarComentario/:codigoComentario',
        views: {
            'menuContent': {
                templateUrl: 'templates/alterarComentario.html',
                controller: 'alterarComentarioCtrl'
            }
        }
    })

    .state('app.alterarTopico', {
        url: '/alterarTopico/:codigoTopico',
        views: {
            'menuContent': {
                templateUrl: 'templates/alterarTopico.html',
                controller: 'alterarTopicoCtrl'
            }
        }
    })

    .state('verTeste', {
        url: '/verTeste/:alunoLogin/:testeCodigo/:codGrupo',
        templateUrl: 'templates/verTeste.html',
        controller: 'verTesteCtrl'
    })

    .state('responderTeste', {
        url: '/responderTeste/:alunoLogin/:testeCodigo/:codGrupo',
        templateUrl: 'templates/responderTeste.html',
        controller: 'responderTesteCtrl'
    })    

    .state('perfil', {
        url: '/perfil/:alunoLogin',
        templateUrl: 'templates/perfil.html',
        controller: 'perfilCtrl'
    })

    .state('configuracoesInfo', {
        url: '/configuracoesInfo/:alunoLogin',
        templateUrl: 'templates/configuracoesInfo.html',
        controller: 'configuracoesInfoCtrl'
    })

    .state('alterarFoto', {
        url: '/alterarFoto/:alunoLogin',
        templateUrl: 'templates/alterarFoto.html',
        controller: 'alterarFotoCtrl'
    })

    .state('app.pesquisarGrupo', {
        url: '/pesquisarGrupo/:alunoLogin',
        views: {
            'menuContent': {
                templateUrl: 'templates/pesquisarGrupo.html',
                controller: 'pesquisarGrupoCtrl'
            }
        }
    })

    .state('app.solicitacaoGrupo', {
        url: '/solicitacaoGrupo/:grupoCodigo/:loginAluno',
        views: {
            'menuContent': {
                templateUrl: 'templates/solicitacaoGrupo.html',
                controller: 'solicitacaoGrupoCtrl'
            }
        }
    })

    .state('app.rancking', {
        url: '/rancking/:codGrupo',
        views: {
            'menuContent': {
                templateUrl: 'templates/rancking.html',
                controller: 'ranckingCtrl'
            }
        }
    })

    .state('arquivos', {
        url: '/arquivos/:codGrupo/:loginAluno',
        templateUrl: 'templates/arquivos.html',
        controller: 'arquivosGrupoCtrl'
    })

    .state('testeResolvido', {
        url: '/testeResolvido/:codTeste',
        templateUrl: 'templates/testeResolvido.html',
        controller: 'testeResolvidoCtrl'
    })

    .state('senha', {
        url: '/senha',
        templateUrl: 'templates/senha.html',
        controller: 'senhaCtrl'
    })

    .state('login', {
        url: '/login',
        templateUrl: 'templates/login.html',
        controller: 'loginCtrl'
    })

    .state('cadastro', {
        url: '/cadastro',
        templateUrl: 'templates/cadastro.html',
        controller: 'alunoCtrl'
    })

    $urlRouterProvider.otherwise('/login');

})
